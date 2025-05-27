package com.example.giziku.database

import com.example.giziku.util.Nutrisi
import kotlinx.coroutines.flow.Flow

class NutrisiRepository(private val dao: NutrisiDao) {

    suspend fun insertNutrisi(nutrisi: Nutrisi) {
        dao.insertNutrisi(nutrisi)
    }

    fun getNutrisiByUserId(userId: Long): Flow<List<Nutrisi>> {
        return dao.getNutrisiByUserID(userId)
    }

    fun getNutrisiById(id: Long): Nutrisi {
        return dao.getNutrisiByID(id)
    }
}