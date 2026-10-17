package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.SavedApplicationEntity
import com.example.data.model.CandidateCategory
import com.example.data.model.CandidateProfile
import com.example.data.model.CorrigendumNotice
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityVerdict
import com.example.data.model.Gender
import com.example.data.model.RecruitmentBoard
import com.example.data.model.RecruitmentNotice
import com.example.data.repository.JobDirectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    RECRUITMENTS,
    CORRIGENDA,
    ELIGIBILITY_CALCULATOR,
    MY_APPLICATIONS,
    DETAIL_VIEW
}

data class JobDirectUiState(
    val isGujarati: Boolean = true,
    val currentScreen: AppScreen = AppScreen.RECRUITMENTS,
    val selectedRecruitmentId: String? = null,
    val searchQuery: String = "",
    val selectedBoard: RecruitmentBoard = RecruitmentBoard.ALL,
    val selectedEducation: EducationLevel = EducationLevel.ANY,
    val onlyWithCorrigendum: Boolean = false,
    val candidateProfile: CandidateProfile = CandidateProfile(
        birthYear = 1999,
        birthMonth = 6,
        birthDay = 15,
        category = CandidateCategory.SEBC,
        gender = Gender.MALE,
        isGujaratDomicile = true,
        isDifferentlyAbled = false,
        isExServiceman = false,
        exServiceYears = 0,
        hasCccCertificate = true,
        educationLevel = EducationLevel.GRADUATE
    ),
    val snackbarMessage: String? = null
)

class JobDirectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobDirectRepository

    init {
        val db = AppDatabase.getInstance(application)
        repository = JobDirectRepository(db)

        viewModelScope.launch {
            repository.seedRoomDatabaseIfEmpty()
            val saved = repository.loadUserProfileFromRoom()
            if (saved != null) {
                _uiState.value = _uiState.value.copy(
                    candidateProfile = saved.first,
                    isGujarati = saved.second
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(JobDirectUiState())
    val uiState: StateFlow<JobDirectUiState> = _uiState.asStateFlow()

    val savedApplications: StateFlow<List<SavedApplicationEntity>> =
        repository.savedApplications.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allRecruitments: List<RecruitmentNotice> = repository.allRecruitments
    val allCorrigenda: List<CorrigendumNotice> = repository.allCorrigenda
    val standardChecklist = repository.standardChecklistItems

    fun toggleLanguage() {
        val newLang = !_uiState.value.isGujarati
        _uiState.value = _uiState.value.copy(isGujarati = newLang)
        viewModelScope.launch {
            repository.saveUserProfileToRoom(_uiState.value.candidateProfile, newLang)
        }
    }

    fun setLanguage(isGujarati: Boolean) {
        _uiState.value = _uiState.value.copy(isGujarati = isGujarati)
        viewModelScope.launch {
            repository.saveUserProfileToRoom(_uiState.value.candidateProfile, isGujarati)
        }
    }

    fun navigateTo(screen: AppScreen, recruitmentId: String? = null) {
        _uiState.value = _uiState.value.copy(
            currentScreen = screen,
            selectedRecruitmentId = recruitmentId ?: _uiState.value.selectedRecruitmentId
        )
    }

    fun openRecruitmentDetail(recruitmentId: String) {
        _uiState.value = _uiState.value.copy(
            selectedRecruitmentId = recruitmentId,
            currentScreen = AppScreen.DETAIL_VIEW
        )
    }

    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun setSelectedBoard(board: RecruitmentBoard) {
        _uiState.value = _uiState.value.copy(selectedBoard = board)
    }

    fun setSelectedEducation(edu: EducationLevel) {
        _uiState.value = _uiState.value.copy(selectedEducation = edu)
    }

    fun toggleOnlyWithCorrigendum() {
        _uiState.value = _uiState.value.copy(onlyWithCorrigendum = !_uiState.value.onlyWithCorrigendum)
    }

    fun updateCandidateProfile(newProfile: CandidateProfile) {
        _uiState.value = _uiState.value.copy(candidateProfile = newProfile)
        viewModelScope.launch {
            repository.saveUserProfileToRoom(newProfile, _uiState.value.isGujarati)
        }
    }

    fun clearSnackbarMessage() {
        _uiState.value = _uiState.value.copy(snackbarMessage = null)
    }

    fun isRecruitmentSaved(recruitmentId: String): Boolean {
        return savedApplications.value.any { it.recruitmentId == recruitmentId }
    }

    fun getSavedApplication(recruitmentId: String): SavedApplicationEntity? {
        return savedApplications.value.find { it.recruitmentId == recruitmentId }
    }

    fun toggleSave(recruitmentId: String) {
        viewModelScope.launch {
            val isCurrentlySaved = isRecruitmentSaved(recruitmentId)
            repository.toggleSaveRecruitment(recruitmentId, isCurrentlySaved)
            val msg = if (isCurrentlySaved) {
                if (_uiState.value.isGujarati) "અરજી ટ્રેકરમાંથી દૂર કરી" else "Removed from saved applications"
            } else {
                if (_uiState.value.isGujarati) "અરજી ટ્રેકરમાં ઉમેરી" else "Added to application tracker"
            }
            _uiState.value = _uiState.value.copy(snackbarMessage = msg)
        }
    }

    fun updateApplicationStage(recruitmentId: String, newStage: String) {
        viewModelScope.launch {
            repository.updateApplicationStage(recruitmentId, newStage)
        }
    }

    fun saveOjasDetails(recruitmentId: String, confirmNo: String, rollNo: String, notes: String) {
        viewModelScope.launch {
            repository.updateOjasDetails(recruitmentId, confirmNo, rollNo, notes)
            val msg = if (_uiState.value.isGujarati) "OJAS વિગતો સાચવી લેવાઈ" else "OJAS details saved successfully"
            _uiState.value = _uiState.value.copy(snackbarMessage = msg)
        }
    }

    fun toggleDocumentCheck(recruitmentId: String, docId: String) {
        viewModelScope.launch {
            repository.toggleChecklistItem(recruitmentId, docId)
        }
    }

    fun getCorrigendaForRecruitment(recruitmentId: String): List<CorrigendumNotice> {
        return repository.getCorrigendaForRecruitment(recruitmentId)
    }

    fun evaluateSingleEligibility(notice: RecruitmentNotice): EligibilityVerdict {
        return repository.evaluateEligibility(notice, _uiState.value.candidateProfile)
    }

    fun getEligibleRecruitmentsList(): List<Pair<RecruitmentNotice, EligibilityVerdict>> {
        return repository.findEligibleRecruitments(_uiState.value.candidateProfile)
    }

    fun getFilteredRecruitments(): List<RecruitmentNotice> {
        val q = _uiState.value.searchQuery.trim().lowercase()
        val board = _uiState.value.selectedBoard
        val edu = _uiState.value.selectedEducation
        val onlyCorr = _uiState.value.onlyWithCorrigendum

        return allRecruitments.filter { notice ->
            val matchesQuery = q.isEmpty() ||
                    notice.titleEn.lowercase().contains(q) ||
                    notice.titleGu.lowercase().contains(q) ||
                    notice.advtNo.lowercase().contains(q) ||
                    notice.departmentEn.lowercase().contains(q) ||
                    notice.departmentGu.lowercase().contains(q)

            val matchesBoard = board == RecruitmentBoard.ALL || notice.board == board

            val matchesEdu = edu == EducationLevel.ANY || notice.educationLevel == edu

            val matchesCorr = !onlyCorr || notice.hasCorrigendum

            matchesQuery && matchesBoard && matchesEdu && matchesCorr
        }
    }
}
