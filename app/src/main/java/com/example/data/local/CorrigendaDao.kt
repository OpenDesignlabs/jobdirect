package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CorrigendaDao {
    @Query("SELECT * FROM corrigenda ORDER BY issueDate DESC")
    fun getAllCorrigenda(): Flow<List<Corrigenda>>

    @Query("SELECT * FROM corrigenda WHERE recruitmentId = :recruitmentId ORDER BY issueDate DESC")
    fun getCorrigendaForVacancy(recruitmentId: String): Flow<List<Corrigenda>>

    @Query("SELECT * FROM corrigenda WHERE changeTypeCode = :changeTypeCode ORDER BY issueDate DESC")
    fun getCorrigendaByType(changeTypeCode: String): Flow<List<Corrigenda>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCorrigenda(corrigendaList: List<Corrigenda>)

    @Query("DELETE FROM corrigenda")
    suspend fun clearAll()
}
