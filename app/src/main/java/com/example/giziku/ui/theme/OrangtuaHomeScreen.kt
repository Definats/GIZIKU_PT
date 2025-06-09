package com.example.giziku.ui.theme

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.giziku.R
import com.example.giziku.model.AnakEntity
import com.example.giziku.model.Growth
import com.example.giziku.util.GrowthViewModel
import com.example.giziku.util.Nutrisi
import com.example.giziku.util.NutrisiViewModel
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun OrangtuaHomeScreen(navController: NavController, growthData: List<Growth>, nutrisi: List<Nutrisi>, anakList: List<AnakEntity>) {
    val context = LocalContext.current

    val application = context.applicationContext as Application
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))

    val edukasiState by userViewModel.getAllEdukasi().observeAsState(emptyList())


    val edukasiList = edukasiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome\nTo\nGiziKu!",
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 56.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (anakList.isEmpty()) {
            // Jika belum ada anak, tampilkan teks dan tombol tambah anak
            Text(text = "Belum ada data anak.", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { navController.navigate("pendaftaran") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369))
            ) {
                Text(text = "Tambah Anak", color = Color.White)
            }
        } else {
            // Tampilkan profil anak (hanya satu anak, sesuai aturan)
            val anak = anakList[0]
            val painter = if (!anak.fileUri.isNullOrEmpty()) {
                rememberAsyncImagePainter(model = File(anak.fileUri!!))
            } else {
                painterResource(id = R.drawable.profile_placeholder)
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate("detailanak/${anak.id}") },
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Nama Anak: ${anak.nama}", fontWeight = FontWeight.Bold)
                        Text("Tanggal Lahir: ${anak.tanggalLahir}", fontSize = 12.sp)
                        Text("Jenis Kelamin: ${anak.jenisKelamin}", fontSize = 12.sp)
                        Text("Kode Unik: ${anak.kodeUnik}", fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Tampilkan tombol Update anak
            Button(
                onClick = { navController.navigate("updateTTBB/${anak.id}") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369))
            ) {
                Text(text = "Update", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu tetap muncul
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                MenuItem(R.drawable.profile_placeholder, "Profile") {
                    navController.navigate("profileorangtua")
                }
                MenuItem(R.drawable.tracing_nutrisi, "Tracker Nutrisi") {
                    navController.navigate("tracker")
                }
                MenuItem(R.drawable.kamera, "Kamera Gizi") {
                    navController.navigate("kamera")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Analisis",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        var recommendation by remember { mutableStateOf("") }

        if (growthData.isNotEmpty() && nutrisi.isNotEmpty()) {
            LaunchedEffect(growthData, nutrisi) {
                Log.d("OrangtuaHomeScreen", "Growth Data: $growthData")
                recommendation = getRecommendation(growthData, nutrisi)
            }
        } else {
            Text(text = "Tidak ada data untuk analisis.", fontSize = 14.sp)
        }

        if (recommendation.isNotEmpty()) {
            Text(
                text = recommendation,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            Text(text = "Sedang memproses rekomendasi...", fontSize = 14.sp)
        }
    }
}

@Composable
fun MenuItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun DetailAnakScreen(
    anakId: Int,
    userViewModel: UserViewModel,
    onBack: () -> Unit,
    navController: NavController
) {
    var profile by remember { mutableStateOf<AnakEntity?>(null) }

    LaunchedEffect(Unit) {
        profile = userViewModel.getAnakById(anakId)
    }
    val painter = if (!profile?.fileUri.isNullOrEmpty()) {
        rememberAsyncImagePainter(model = File(profile!!.fileUri!!))
    } else {
        painterResource(id = R.drawable.profile_placeholder)
    }

    profile?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F6F2))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(

                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) { // Kembali ke Home
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF127369)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Detail Anak", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            profile?.let {
                // Foto Profil
                Image(
                    painter = painter,
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Informasi Anak
                ProfileItem(label = "Nama Anak", value = it.nama)
                ProfileItem(label = "Jenis Kelamin", value = it.jenisKelamin)
                ProfileItem(label = "Tanggal Lahir", value = it.tanggalLahir)
                ProfileItem(label = "Tinggi Badan", value = it.tinggiBadan)
                ProfileItem(label = "Berat Badan", value = it.beratBadan)
                ProfileItem(label = "Kode Unik", value = it.kodeUnik)

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Tombol Edit
                    Button(
                        onClick = { navController.navigate("editAnak/$anakId") },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369)),
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Edit", color = Color.White)
                    }

                    // Tombol Hapus
                    Button(
                        onClick = {
                            userViewModel.deleteAnak(anakId)
                            onBack() // Setelah hapus, kembali ke halaman sebelumnya
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369)),
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Hapus", color = Color.White)
                    }
                }

            }
        }
    }
}

@Composable
fun EditAnakScreen(
    anakId: Int,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var anak by remember { mutableStateOf<AnakEntity?>(null) }
    val fileUri = remember { mutableStateOf<Uri?>(null) }

    var nama by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var tinggiBadan by remember { mutableStateOf("") }
    var beratBadan by remember { mutableStateOf("") }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        anak = userViewModel.getAnakById(anakId)
        anak?.let {
            nama = it.nama
            jenisKelamin = it.jenisKelamin
            tanggalLahir = it.tanggalLahir
            tinggiBadan = it.tinggiBadan
            beratBadan = it.beratBadan
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        fileUri.value = uri
    }
// Tambahkan di dalam composable setelah deklarasi tanggalLahir
    val calendar = Calendar.getInstance()
    val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }

    val showDatePicker = {
        val initialCalendar = Calendar.getInstance()
        if (tanggalLahir.isNotEmpty()) {
            try {
                val date = dateFormatter.parse(tanggalLahir)
                if (date != null) initialCalendar.time = date
            } catch (_: Exception) {}
        }

        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                tanggalLahir = dateFormatter.format(calendar.time)
            },
            initialCalendar.get(Calendar.YEAR),
            initialCalendar.get(Calendar.MONTH),
            initialCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    anak?.let {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) { // Kembali ke Home
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF127369)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit Profile Anak", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                // Jika ada gambar baru yang dipilih, tampilkan gambar itu
                Spacer(modifier = Modifier.height(8.dp))
                if (fileUri.value != null) {
                    Image(
                        painter = rememberAsyncImagePainter(fileUri.value),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape), // Memastikan gambar berbentuk lingkaran
                        contentScale = ContentScale.Crop // Memastikan gambar memenuhi lingkaran tanpa melampaui
                    )
                } else {
                    // Jika belum ada gambar baru, tampilkan gambar placeholder
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape), // Memastikan gambar berbentuk lingkaran
                        contentScale = ContentScale.Crop // Memastikan gambar memenuhi lingkaran tanpa melampaui
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            InputField("Nama", nama) { nama = it }
            InputField("Jenis Kelamin", jenisKelamin) { jenisKelamin = it }
            OutlinedTextField(
                value = tanggalLahir,
                onValueChange = { tanggalLahir = it }, // agar tetap editable jika mau
                readOnly = true, // supaya tidak bisa edit manual
                label = { Text("Tanggal Lahir") },
                trailingIcon = {
                    IconButton(onClick = showDatePicker) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = "Pilih Tanggal"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            InputField("Tinggi Badan", tinggiBadan) { tinggiBadan = it }
            InputField("Berat Badan", beratBadan) { beratBadan = it }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val selectedUri = fileUri.value
                    val copiedPath = selectedUri?.let {
                        copyImageToInternalStorageAnak1(
                            context,
                            it,
                            "anak_${System.currentTimeMillis()}.jpg"
                        )
                    }
                val updated = it.copy(
                    nama = nama,
                    jenisKelamin = jenisKelamin,
                    tanggalLahir = tanggalLahir,
                    tinggiBadan = tinggiBadan,
                    beratBadan = beratBadan,
                    fileUri = copiedPath ?: anak!!.fileUri
                )
                userViewModel.updateAnak(updated)
                navController.popBackStack() // Kembali ke detail
            }, // Simpan dan kembali ke Home
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF127369)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(text = "Simpan", color = Color.White, fontSize = 16.sp)
            }
        }
    } ?: Text("Memuat data anak...")
}

fun copyImageToInternalStorageAnak1(context: Context, uri: Uri, filename: String): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, filename)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label)
        androidx.compose.material3.OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

suspend fun getRecommendation(
    growthData: List<Growth>,
    nutrisiData: List<Nutrisi>
): String {
    val model = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = "AIzaSyCpJ61DKwKdbz2XfAGRuQLAGoQG1ykNR7k",
    )

    val content = Content.Builder()
        .text(
            """
            Dari data-data ini, buat rekomendasi gizi untuk anak:
            Growth Data:
            ${growthData.joinToString("\n") { "Date: ${it.tanggal}, Height: ${it.tinggiBadan}, Weight: ${it.beratBadan}" }}
            Nutritional Data:
            ${nutrisiData.joinToString("\n") { "Date: ${it.createdAt}, Calories: ${it.kalori}, Protein: ${it.protein}, Fat: ${it.lemak}, Carbs: ${it.karbohidrat}" }}
                """.trimIndent()
        )
        .build()

    val answer = model.generateContent(content).text ?:
    "Failed to generate content"

    return answer
}