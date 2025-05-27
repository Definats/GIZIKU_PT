package com.example.giziku.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query

import com.example.giziku.util.Nutrisi;
import kotlinx.coroutines.flow.Flow

@Dao
interface NutrisiDao {
    @Insert
    suspend fun insertNutrisi(nutrisi: Nutrisi)

    @Query("SELECT * FROM nutrisi WHERE userId = :userId")
    fun getNutrisiByUserID(userId: Long): Flow<List<Nutrisi>>

    @Query("SELECT * FROM nutrisi WHERE id = :id")
    fun getNutrisiByID(id: Long): Nutrisi
}
