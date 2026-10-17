package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing candidate profile and personalized eligibility criteria.
 * Supports persistent DOB, statutory reservation category, domicile, and language preferences.
 */
@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,
    val birthYear: Int = 1999,
    val birthMonth: Int = 6,
    val birthDay: Int = 15,
    val categoryCode: String = "SEBC", // GENERAL, EWS, SEBC, SC, ST
    val genderCode: String = "MALE",   // MALE, FEMALE
    val isGujaratDomicile: Boolean = true,
    val isDifferentlyAbled: Boolean = false,
    val isExServiceman: Boolean = false,
    val exServiceYears: Int = 0,
    val hasCccCertificate: Boolean = true,
    val educationLevelCode: String = "GRADUATE",
    val isGujaratiPreferred: Boolean = true
)
