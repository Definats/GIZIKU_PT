package com.example.giziku.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.giziku.util.Nutrisi
import com.example.giziku.util.NutrisiViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriTracScreen(
    nutrisiViewModel: NutrisiViewModel,
    userId: Long,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    var isSaved by remember { mutableStateOf(false) }

    val nutrisi by nutrisiViewModel.nutrisi.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
       nutrisiViewModel.getNutrisiByUserId(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Gizi") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(nutrisi) { nutrisi ->
                NutrisiItem(nutrisi = nutrisi)
            }
        }
    }
}

@Composable
fun NutrisiItem(nutrisi: Nutrisi) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text("Kalori: ${nutrisi.kalori}", style = MaterialTheme.typography.titleMedium)
            Text("Protein: ${nutrisi.protein}g")
            Text("Karbohidrat: ${nutrisi.karbohidrat}g")
            Text("Lemak: ${nutrisi.lemak}g")
            Text("Serat: ${nutrisi.serat}g")
            Text("Gula: ${nutrisi.gula}g")
            Text("Natrium: ${nutrisi.natrium}mg")
            Text("Kolesterol: ${nutrisi.kolesterol}mg")
            Text("Vitamin: ${nutrisi.vitamin}mg")
            Text("Waktu: ${formatInstantCompat(nutrisi.createdAt)}")
        }
    }
}

fun formatInstantCompat(instant: Instant, pattern: String = "dd MMM yyyy, HH:mm", timeZone: TimeZone = TimeZone.getDefault()): String {
    val date = Date(instant.toEpochMilliseconds())
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(date)
}