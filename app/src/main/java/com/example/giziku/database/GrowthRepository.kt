package com.example.giziku.database

import com.example.giziku.model.Growth
import kotlinx.coroutines.flow.Flow

class GrowthRepository(private val dao: GrowthDao) {

    suspend fun insertGrowth(growth: Growth) {
        dao.insertGrowth(growth)
    }

    fun getGrowthByAnakId(anakId: Long): Flow<List<Growth>> {
        return dao.getGrowthByAnakId(anakId)
    }
}