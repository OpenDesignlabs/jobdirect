package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_applications")
data class SavedApplicationEntity(
    @PrimaryKey
    val recruitmentId: String,
    val savedAtTimestamp: Long = System.currentTimeMillis(),
    val applicationStage: String = "SAVED", // SAVED, DOCS_READY, APPLIED_OJAS, ADMIT_CARD, APPEARED
    val ojasConfirmationNumber: String = "",
    val ojasApplicationNumber: String = "",
    val candidateNotes: String = "",
    val completedDocumentIdsCommaSeparated: String = "" // e.g. "photo,sign,degree"
)
