package com.example.giziku.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.giziku.model.Growth
import kotlinx.coroutines.flow.Flow

@Dao
interface GrowthDao {
    @Insert
    suspend fun insertGrowth(growth: Growth)

    @Query("SELECT * FROM growth WHERE anakId = :anakId")
    fun getGrowthByAnakId(anakId: Long) : Flow<List<Growth>>
}