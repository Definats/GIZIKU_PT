package com.example.giziku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giziku.database.GrowthRepository
import com.example.giziku.database.NutrisiRepository

class GrowthViewModelFactory(
    private val repository: GrowthRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GrowthViewModel::class.java)) {
            return GrowthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}