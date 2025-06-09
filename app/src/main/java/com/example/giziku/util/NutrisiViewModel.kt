package com.example.giziku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giziku.database.NutrisiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class NutrisiViewModel(private val repository: NutrisiRepository) : ViewModel() {

    private val _nutrisi = MutableStateFlow<List<Nutrisi>>(emptyList())
    val nutrisi: StateFlow<List<Nutrisi>> = _nutrisi.asStateFlow()

    fun insertNutrisi(nutrisi: Nutrisi) {
        viewModelScope.launch {
            repository.insertNutrisi(nutrisi)
        }
    }

    fun getNutrisiByUserId(userId: Long) {
        viewModelScope.launch {
            repository.getNutrisiByUserId(userId)
                .collect { list ->
                    _nutrisi.value = list
                }
        }
    }

    suspend fun getNutrisiById(id: Long): Nutrisi {
        return repository.getNutrisiById(id)
    }
}
