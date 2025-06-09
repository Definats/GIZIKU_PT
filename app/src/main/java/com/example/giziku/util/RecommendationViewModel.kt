package com.example.giziku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giziku.model.Growth
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecommendationViewModel(
    private val generativeApiKey: String = "AIzaSyCpJ61DKwKdbz2XfAGRuQLAGoQG1ykNR7k",
    private val modelName: String = "gemini-2.0-flash"
) : ViewModel() {

    private val _recommendation = MutableStateFlow<String?>(null)
    val recommendation: StateFlow<String?> = _recommendation

    fun loadRecommendation(
        growthData: List<Growth>,
        nutrisiData: List<Nutrisi>
    ) {
        viewModelScope.launch {
            _recommendation.value = "Sedang memproses rekomendasi..."
            val result = generateRecommendation(growthData, nutrisiData)
            _recommendation.value = result
        }
    }

    private suspend fun generateRecommendation(
        growthData: List<Growth>,
        nutrisiData: List<Nutrisi>
    ): String = withContext(Dispatchers.IO) {
        val model = GenerativeModel(
            modelName = modelName,
            apiKey = generativeApiKey
        )

        val promptText = buildString {
            append("Dari data-data ini, buat rekomendasi gizi untuk anak:\n")
            append("Growth Data:\n")
            growthData.forEach {
                append("Date: ${it.tanggal}, Height: ${it.tinggiBadan}, Weight: ${it.beratBadan}\n")
            }
            append("Nutritional Data:\n")
            nutrisiData.forEach {
                append("Date: ${it.createdAt}, Calories: ${it.kalori}, Protein: ${it.protein}, Fat: ${it.lemak}, Carbs: ${it.karbohidrat}\n")
            }
            append("Pastikan tidak lebih dari 500 kata dan gunakan bahasa yang mudah dimengerti orang tua.\n")
        }

        val content = Content.Builder()
            .text(promptText)
            .build()

        model.generateContent(content).text ?: "Failed to generate content"
    }
}
