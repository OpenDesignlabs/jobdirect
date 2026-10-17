package com.example.data.model

data class CandidateProfile(
    val birthYear: Int = 1999,
    val birthMonth: Int = 6, // 1-12
    val birthDay: Int = 15,  // 1-31
    val category: CandidateCategory = CandidateCategory.GENERAL,
    val gender: Gender = Gender.MALE,
    val isGujaratDomicile: Boolean = true,
    val isDifferentlyAbled: Boolean = false,
    val isExServiceman: Boolean = false,
    val exServiceYears: Int = 0,
    val hasCccCertificate: Boolean = true,
    val educationLevel: EducationLevel = EducationLevel.GRADUATE
)

enum class EligibilityStatus {
    ELIGIBLE,
    AGE_OVER_LIMIT,
    AGE_UNDER_LIMIT,
    EDUCATION_MISMATCH,
    DOMICILE_REQUIRED,
    MANUAL_VERIFICATION_RECOMMENDED
}

data class EligibilityVerdict(
    val status: EligibilityStatus,
    val calculatedAgeYears: Int,
    val calculatedAgeMonths: Int,
    val calculatedAgeDays: Int,
    val permissibleMaxAge: Int,
    val baseMaxAge: Int,
    val totalRelaxationYears: Int,
    val statusTitleEn: String,
    val statusTitleGu: String,
    val explanationEn: String,
    val explanationGu: String,
    val appliedRelaxationsEn: List<String>,
    val appliedRelaxationsGu: List<String>
)

data class DocumentChecklistItem(
    val id: String,
    val nameEn: String,
    val nameGu: String,
    val specsEn: String,
    val specsGu: String,
    val applicableCategories: List<String>, // e.g. "ALL", "SEBC", "EWS", "SC", "ST"
    val isMandatoryAtOjasApply: Boolean,
    val ojasInstructionEn: String,
    val ojasInstructionGu: String
)
