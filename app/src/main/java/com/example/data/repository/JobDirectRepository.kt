package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.Corrigenda
import com.example.data.local.CorrigendaDao
import com.example.data.local.RecruitmentVacancy
import com.example.data.local.RecruitmentVacancyDao
import com.example.data.local.SavedApplicationDao
import com.example.data.local.SavedApplicationEntity
import com.example.data.local.UserProfile
import com.example.data.local.UserProfileDao
import com.example.data.model.CandidateCategory
import com.example.data.model.CandidateProfile
import com.example.data.model.CorrigendumNotice
import com.example.data.model.DocumentChecklistItem
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityVerdict
import com.example.data.model.Gender
import com.example.data.model.RecruitmentNotice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JobDirectRepository(
    private val appDatabase: AppDatabase
) {
    private val savedApplicationDao: SavedApplicationDao = appDatabase.savedApplicationDao()
    private val recruitmentVacancyDao: RecruitmentVacancyDao = appDatabase.recruitmentVacancyDao()
    private val corrigendaDao: CorrigendaDao = appDatabase.corrigendaDao()
    private val userProfileDao: UserProfileDao = appDatabase.userProfileDao()

    val allRecruitments: List<RecruitmentNotice> = VerifiedGujaratRecruitmentData.verifiedRecruitments
    val allCorrigenda: List<CorrigendumNotice> = VerifiedGujaratRecruitmentData.verifiedCorrigenda
    val standardChecklistItems: List<DocumentChecklistItem> = VerifiedGujaratRecruitmentData.standardChecklistItems

    val savedApplications: Flow<List<SavedApplicationEntity>> =
        savedApplicationDao.getAllSavedApplications()

    val userProfileFlow: Flow<UserProfile?> =
        userProfileDao.getUserProfile()

    /**
     * Seeds initial verified Gujarat recruitment vacancies and corrigenda change alerts
     * into Room database for offline durability and persistent SQL queries.
     */
    suspend fun seedRoomDatabaseIfEmpty() {
        val vacancies = allRecruitments.map { notice ->
            RecruitmentVacancy(
                id = notice.id,
                advtNo = notice.advtNo,
                boardCode = notice.board.code,
                titleEn = notice.titleEn,
                titleGu = notice.titleGu,
                postCadreEn = notice.postCadreEn,
                postCadreGu = notice.postCadreGu,
                departmentEn = notice.departmentEn,
                departmentGu = notice.departmentGu,
                totalVacancies = notice.totalVacancies,
                vacanciesBreakdownEn = notice.vacanciesBreakdownEn,
                vacanciesBreakdownGu = notice.vacanciesBreakdownGu,
                payScaleEn = notice.payScaleEn,
                payScaleGu = notice.payScaleGu,
                applicationStartDate = notice.applicationStartDate,
                applicationEndDate = notice.applicationEndDate,
                isDeadlineExtended = notice.isDeadlineExtended,
                originalEndDate = notice.originalEndDate,
                examDateEstimated = notice.examDateEstimated,
                cutoffDateForAge = notice.cutoffDateForAge,
                minAge = notice.minAge,
                maxAgeGeneral = notice.maxAgeGeneral,
                educationLevelCode = notice.educationLevel.name,
                educationDetailsEn = notice.educationDetailsEn,
                educationDetailsGu = notice.educationDetailsGu,
                isCccRequired = notice.isCccRequired,
                hasCorrigendum = notice.hasCorrigendum,
                corrigendumCount = notice.corrigendumCount,
                officialPdfUrl = notice.officialPdfUrl,
                applyPortalUrl = notice.applyPortalUrl,
                lastVerifiedDate = notice.lastVerifiedDate,
                verificationNotesEn = notice.verificationNotesEn,
                verificationNotesGu = notice.verificationNotesGu,
                feeDetailsEn = notice.feeDetailsEn,
                feeDetailsGu = notice.feeDetailsGu
            )
        }
        recruitmentVacancyDao.insertVacancies(vacancies)

        val corrigendaEntities = allCorrigenda.map { corr ->
            Corrigenda(
                id = corr.id,
                recruitmentId = corr.recruitmentId,
                advtNo = corr.advtNo,
                boardCode = corr.board.code,
                corrigendumNo = corr.corrigendumNo,
                issueDate = corr.issueDate,
                changeTypeCode = corr.changeType.name,
                headlineEn = corr.headlineEn,
                headlineGu = corr.headlineGu,
                summaryEn = corr.summaryEn,
                summaryGu = corr.summaryGu,
                originalValueTextEn = corr.originalValueTextEn,
                originalValueTextGu = corr.originalValueTextGu,
                revisedValueTextEn = corr.revisedValueTextEn,
                revisedValueTextGu = corr.revisedValueTextGu,
                affectedSectionOrRule = corr.affectedSectionOrRule,
                officialNoticePdfUrl = corr.officialNoticePdfUrl
            )
        }
        corrigendaDao.insertCorrigenda(corrigendaEntities)
    }

    suspend fun saveUserProfileToRoom(profile: CandidateProfile, isGujarati: Boolean) {
        val entity = UserProfile(
            id = 1,
            birthYear = profile.birthYear,
            birthMonth = profile.birthMonth,
            birthDay = profile.birthDay,
            categoryCode = profile.category.name,
            genderCode = profile.gender.name,
            isGujaratDomicile = profile.isGujaratDomicile,
            isDifferentlyAbled = profile.isDifferentlyAbled,
            isExServiceman = profile.isExServiceman,
            exServiceYears = profile.exServiceYears,
            hasCccCertificate = profile.hasCccCertificate,
            educationLevelCode = profile.educationLevel.name,
            isGujaratiPreferred = isGujarati
        )
        userProfileDao.saveUserProfile(entity)
    }

    suspend fun loadUserProfileFromRoom(): Pair<CandidateProfile, Boolean>? {
        val entity = userProfileDao.getUserProfileSync() ?: return null
        val profile = CandidateProfile(
            birthYear = entity.birthYear,
            birthMonth = entity.birthMonth,
            birthDay = entity.birthDay,
            category = runCatching { CandidateCategory.valueOf(entity.categoryCode) }.getOrDefault(CandidateCategory.SEBC),
            gender = runCatching { Gender.valueOf(entity.genderCode) }.getOrDefault(Gender.MALE),
            isGujaratDomicile = entity.isGujaratDomicile,
            isDifferentlyAbled = entity.isDifferentlyAbled,
            isExServiceman = entity.isExServiceman,
            exServiceYears = entity.exServiceYears,
            hasCccCertificate = entity.hasCccCertificate,
            educationLevel = runCatching { EducationLevel.valueOf(entity.educationLevelCode) }.getOrDefault(EducationLevel.GRADUATE)
        )
        return profile to entity.isGujaratiPreferred
    }

    fun getRecruitmentById(id: String): RecruitmentNotice? {
        return allRecruitments.find { it.id == id }
    }

    fun getCorrigendaForRecruitment(recruitmentId: String): List<CorrigendumNotice> {
        return allCorrigenda.filter { it.recruitmentId == recruitmentId }
    }

    fun observeSavedStatus(recruitmentId: String): Flow<SavedApplicationEntity?> {
        return savedApplicationDao.observeSavedApplication(recruitmentId)
    }

    suspend fun toggleSaveRecruitment(recruitmentId: String, isCurrentlySaved: Boolean) {
        if (isCurrentlySaved) {
            savedApplicationDao.deleteSavedApplication(recruitmentId)
        } else {
            savedApplicationDao.upsertSavedApplication(
                SavedApplicationEntity(
                    recruitmentId = recruitmentId,
                    savedAtTimestamp = System.currentTimeMillis(),
                    applicationStage = "SAVED"
                )
            )
        }
    }

    suspend fun updateApplicationStage(recruitmentId: String, newStage: String) {
        val existing = savedApplicationDao.getSavedApplicationById(recruitmentId)
        val entity = existing?.copy(applicationStage = newStage)
            ?: SavedApplicationEntity(
                recruitmentId = recruitmentId,
                applicationStage = newStage
            )
        savedApplicationDao.upsertSavedApplication(entity)
    }

    suspend fun updateOjasDetails(
        recruitmentId: String,
        confirmationNo: String,
        rollNo: String,
        notes: String
    ) {
        val existing = savedApplicationDao.getSavedApplicationById(recruitmentId)
        val entity = existing?.copy(
            ojasConfirmationNumber = confirmationNo,
            ojasApplicationNumber = rollNo,
            candidateNotes = notes
        ) ?: SavedApplicationEntity(
            recruitmentId = recruitmentId,
            ojasConfirmationNumber = confirmationNo,
            ojasApplicationNumber = rollNo,
            candidateNotes = notes
        )
        savedApplicationDao.upsertSavedApplication(entity)
    }

    suspend fun toggleChecklistItem(recruitmentId: String, docId: String) {
        val existing = savedApplicationDao.getSavedApplicationById(recruitmentId)
            ?: SavedApplicationEntity(recruitmentId = recruitmentId)
        val currentIds = existing.completedDocumentIdsCommaSeparated
            .split(",")
            .filter { it.isNotBlank() }
            .toMutableSet()

        if (currentIds.contains(docId)) {
            currentIds.remove(docId)
        } else {
            currentIds.add(docId)
        }

        val updated = existing.copy(
            completedDocumentIdsCommaSeparated = currentIds.joinToString(",")
        )
        savedApplicationDao.upsertSavedApplication(updated)
    }

    fun evaluateEligibility(notice: RecruitmentNotice, profile: CandidateProfile): EligibilityVerdict {
        return VerifiedGujaratRecruitmentData.calculateEligibility(notice, profile)
    }

    fun findEligibleRecruitments(profile: CandidateProfile): List<Pair<RecruitmentNotice, EligibilityVerdict>> {
        return allRecruitments.map { notice ->
            notice to evaluateEligibility(notice, profile)
        }
    }
}
