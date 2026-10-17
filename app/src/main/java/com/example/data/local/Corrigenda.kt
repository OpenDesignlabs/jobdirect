package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing official recruitment Corrigenda and amendment notices (સુધારા વધારા નોટિસ).
 * Powers the source-backed change audit trail and diff tracker.
 */
@Entity(tableName = "corrigenda")
data class Corrigenda(
    @PrimaryKey
    val id: String,
    val recruitmentId: String,
    val advtNo: String,
    val boardCode: String,
    val corrigendumNo: String,
    val issueDate: String, // YYYY-MM-DD
    val changeTypeCode: String, // DEADLINE_EXTENSION, VACANCY_REVISION, ELIGIBILITY_AMENDMENT, SYLLABUS_CHANGE, EXAM_DATE_RESCHEDULE
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
