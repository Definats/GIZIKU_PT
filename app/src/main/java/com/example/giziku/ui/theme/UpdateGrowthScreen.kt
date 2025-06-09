package com.example.giziku.ui.theme

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.giziku.database.AppDatabase
import com.example.giziku.database.GrowthDao
import com.example.giziku.database.GrowthRepository
import com.example.giziku.model.Growth
import com.example.giziku.util.GrowthViewModel
import com.example.giziku.util.GrowthViewModelFactory
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.time.ZoneOffset
import java.util.Calendar

@Composable
fun UpdateGrowthScreen(navController: NavController, anakId: Long, viewModel: GrowthViewModel) {
    var tanggal by rememberSaveable { mutableStateOf("") }
    var beratBadan by rememberSaveable { mutableStateOf("") }
    var tinggiBadan by rememberSaveable { mutableStateOf("") }

    val growthHistory = viewModel.growthData.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    LaunchedEffect(Unit) {
        viewModel.loadGrowth(anakId)
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val formattedDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
            tanggal = formattedDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF127369)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Update Data Pertumbuhan", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = tanggal,
            onValueChange = { },
            readOnly = true,
            label = { Text("Tanggal Pertumbuhan") },
            trailingIcon = {
                IconButton(onClick = {
                    datePickerDialog.show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "Pilih Tanggal"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015249),
                unfocusedBorderColor = Color(0xFF015249),
                focusedLabelColor = Color(0xFF015249)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = beratBadan,
            onValueChange = { input ->
                if (input.length <= 3 && input.all { it.isDigit() }) {
                    beratBadan = input
                }
            },
            label = { Text("Berat Badan (Kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015249),
                unfocusedBorderColor = Color(0xFF015249),
                focusedLabelColor = Color(0xFF015249)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tinggiBadan,
            onValueChange = { input ->
                if (input.length <= 3 && input.all { it.isDigit() }) {
                    tinggiBadan = input
                }
            },
            label = { Text("Tinggi Badan (Cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF015249),
                unfocusedBorderColor = Color(0xFF015249),
                focusedLabelColor = Color(0xFF015249)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (tanggal.isNotEmpty() && beratBadan.isNotEmpty() && tinggiBadan.isNotEmpty()) {
                    viewModel.insertGrowth(

                        Growth(tanggal = parseTanggalToInstant(tanggal), tinggiBadan = tinggiBadan, beratBadan = beratBadan, anakId = anakId)
                    )
                    tanggal = ""
                    beratBadan = ""
                    tinggiBadan = ""
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF015249))
        ) {
            Text("Simpan", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Riwayat Perkembangan",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        GrowthTable(data = growthHistory.value)
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(0.5.dp, Color.Black)
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun GrowthTable(data: List<Growth>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TableCell(text = "Tanggal", modifier = Modifier.weight(1f))
            TableCell(text = "TB (cm)", modifier = Modifier.weight(1f))
            TableCell(text = "BB (kg)", modifier = Modifier.weight(1f))
        }

        data.forEach { item ->
            val tgl = item.tanggal
            val tb = item.tinggiBadan
            val bb = item.beratBadan
            Row(modifier = Modifier.fillMaxWidth()) {
                TableCell(text = tgl.toString(), modifier = Modifier.weight(1f))
                TableCell(text = tb, modifier = Modifier.weight(1f))
                TableCell(text = bb, modifier = Modifier.weight(1f))
            }
        }
    }
}

fun parseTanggalToInstant(tanggalStr: String): Instant {
    val localDate = LocalDate.parse(tanggalStr)
    val localDateTime = LocalDateTime(localDate.year, localDate.month, localDate.dayOfMonth, 0, 0)
    return localDateTime.toInstant(TimeZone.UTC)
}