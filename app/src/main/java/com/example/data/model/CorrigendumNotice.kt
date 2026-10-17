package com.example.data.model

enum class CorrigendumType(
    val badgeLabelEn: String,
    val badgeLabelGu: String
) {
    DEADLINE_EXTENSION("Deadline Extended", "અરજી મુદતમાં વધારો"),
    VACANCY_REVISION("Vacancies Revised", "જગ્યાઓમાં ફેરફાર"),
    ELIGIBILITY_AMENDMENT("Eligibility Amendment", "શૈક્ષણિક/વય લાયકાતમાં સુધારો"),
    SYLLABUS_CHANGE("Syllabus / Pattern Change", "પરીક્ષા પદ્ધતિમાં સુધારો"),
    EXAM_DATE_RESCHEDULE("Exam Date Rescheduled", "પરીક્ષા તારીખમાં ફેરફાર")
}

data class CorrigendumNotice(
    val id: String,
    val recruitmentId: String,
    val advtNo: String,
    val board: RecruitmentBoard,
    val corrigendumNo: String,
    val issueDate: String, // YYYY-MM-DD
    val changeType: CorrigendumType,
    val headlineEn: String,
    val headlineGu: String,
    val summaryEn: String,
    val summaryGu: String,
    val originalValueTextEn: String,
    val originalValueTextGu: String,
    val revisedValueTextEn: String,
    val revisedValueTextGu: String,
    val affectedSectionOrRule: String,
    val officialNoticePdfUrl: String
)
