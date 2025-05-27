package com.example.giziku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giziku.database.GrowthRepository
import com.example.giziku.model.Growth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GrowthViewModel(private val repository: GrowthRepository) : ViewModel() {

    private val _growthData = MutableStateFlow<List<Growth>>(emptyList())
    val growthData: StateFlow<List<Growth>> get() = _growthData

    fun loadGrowth(anakId: Long) {
        viewModelScope.launch {
            repository.getGrowthByAnakId(anakId).collect { list ->
                _growthData.value = list
            }
        }
    }

    fun insertGrowth(growth: Growth) {
        viewModelScope.launch {
            repository.insertGrowth(growth)
            // Tidak perlu trigger manual karena Room emit otomatis lewat Flow
        }
    }
}