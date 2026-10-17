package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedApplicationDao {
    @Query("SELECT * FROM saved_applications ORDER BY savedAtTimestamp DESC")
    fun getAllSavedApplications(): Flow<List<SavedApplicationEntity>>

    @Query("SELECT * FROM saved_applications WHERE recruitmentId = :recruitmentId LIMIT 1")
    suspend fun getSavedApplicationById(recruitmentId: String): SavedApplicationEntity?

    @Query("SELECT * FROM saved_applications WHERE recruitmentId = :recruitmentId LIMIT 1")
    fun observeSavedApplication(recruitmentId: String): Flow<SavedApplicationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSavedApplication(entity: SavedApplicationEntity)

    @Query("DELETE FROM saved_applications WHERE recruitmentId = :recruitmentId")
    suspend fun deleteSavedApplication(recruitmentId: String)
}
