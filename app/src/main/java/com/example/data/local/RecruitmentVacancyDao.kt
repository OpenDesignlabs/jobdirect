package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecruitmentVacancyDao {
    @Query("SELECT * FROM recruitment_vacancies ORDER BY applicationEndDate ASC")
    fun getAllVacancies(): Flow<List<RecruitmentVacancy>>

    @Query("SELECT * FROM recruitment_vacancies WHERE id = :id LIMIT 1")
    suspend fun getVacancyById(id: String): RecruitmentVacancy?

    @Query("SELECT * FROM recruitment_vacancies WHERE boardCode = :boardCode ORDER BY applicationEndDate ASC")
    fun getVacanciesByBoard(boardCode: String): Flow<List<RecruitmentVacancy>>

    @Query("SELECT * FROM recruitment_vacancies WHERE hasCorrigendum = 1 ORDER BY applicationEndDate ASC")
    fun getVacanciesWithCorrigenda(): Flow<List<RecruitmentVacancy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancies(vacancies: List<RecruitmentVacancy>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: RecruitmentVacancy)

    @Query("DELETE FROM recruitment_vacancies")
    suspend fun clearAll()
}
