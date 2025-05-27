package com.example.giziku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giziku.database.NutrisiRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class NutrisiViewModel(private val repository: NutrisiRepository) : ViewModel() {

    fun insertNutrisi(nutrisi: Nutrisi) {
        viewModelScope.launch {
            repository.insertNutrisi(nutrisi)
        }
    }

    fun getNutrisiByUserId(userId: Long): StateFlow<List<Nutrisi>> {
        return repository.getNutrisiByUserId(userId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun getNutrisiById(id: Long): Nutrisi {
        return repository.getNutrisiById(id)
    }
}