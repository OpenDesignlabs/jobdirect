package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing verified Gujarat government recruitment vacancy data.
 * Backs offline accessibility, search indexing, and official notification registry.
 */
@Entity(tableName = "recruitment_vacancies")
data class RecruitmentVacancy(
    @PrimaryKey
    val id: String,
    val advtNo: String,
    val boardCode: String, // GPSC, GSSSB, POLICE, GPSSB, HIGH_COURT, POWER_CORP
    val titleEn: String,
    val titleGu: String,
    val postCadreEn: String,
    val postCadreGu: String,
    val departmentEn: String,
    val departmentGu: String,
    val totalVacancies: Int,
    val vacanciesBreakdownEn: String,
    val vacanciesBreakdownGu: String,
    val payScaleEn: String,
    val payScaleGu: String,
    val applicationStartDate: String, // YYYY-MM-DD
    val applicationEndDate: String,   // YYYY-MM-DD
    val isDeadlineExtended: Boolean,
    val originalEndDate: String? = null,
    val examDateEstimated: String,
    val cutoffDateForAge: String,
    val minAge: Int,
    val maxAgeGeneral: Int,
    val educationLevelCode: String, // SSC_10TH, HSC_12TH, GRADUATE, ENGINEERING, COMMERCE_CA, MEDICAL_NURSING
    val educationDetailsEn: String,
    val educationDetailsGu: String,
    val isCccRequired: Boolean,
    val hasCorrigendum: Boolean,
    val corrigendumCount: Int,
    val officialPdfUrl: String,
    val applyPortalUrl: String,
    val lastVerifiedDate: String,
    val verificationNotesEn: String,
    val verificationNotesGu: String,
    val feeDetailsEn: String,
    val feeDetailsGu: String
)
