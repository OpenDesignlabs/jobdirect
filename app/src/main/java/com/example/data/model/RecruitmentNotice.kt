package com.example.data.model

data class RecruitmentNotice(
    val id: String,
    val advtNo: String,
    val board: RecruitmentBoard,
    val titleEn: String,
    val titleGu: String,
    val postCadreEn: String, // e.g. "Class 1", "Class 2", "Class 3"
    val postCadreGu: String, // e.g. "વર્ગ-૧", "વર્ગ-૨", "વર્ગ-૩"
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
    val educationLevel: EducationLevel,
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
    val feeDetailsGu: String,
    val stagesEn: List<String>,
    val stagesGu: List<String>,
    val requiredDocumentIds: List<String>
)
