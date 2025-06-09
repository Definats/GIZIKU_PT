package com.example.giziku

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.giziku.database.AppDatabase
import com.example.giziku.database.GrowthRepository
import com.example.giziku.database.NutrisiRepository
import com.example.giziku.ui.theme.Awal
import com.example.giziku.ui.theme.CameraView
import com.example.giziku.ui.theme.DetailAnakScreen
import com.example.giziku.ui.theme.EditAnakScreen
import com.example.giziku.ui.theme.EditProfileScreen
import com.example.giziku.ui.theme.EdukasiGiziMedis
import com.example.giziku.ui.theme.GrafikScreen
import com.example.giziku.ui.theme.GuruLaporanGizianakScreen
import com.example.giziku.ui.theme.GuruTambahSiswaScreen
import com.example.giziku.ui.theme.Login
import com.example.giziku.ui.theme.MedisHomeScreen
import com.example.giziku.ui.theme.NutriTracScreen
import com.example.giziku.ui.theme.OrangtuaEditProfileScreen
import com.example.giziku.ui.theme.OrangtuaHomeScreen
import com.example.giziku.ui.theme.OrangtuaPendaftarananakScreen
import com.example.giziku.ui.theme.OrangtuaProfileScreen

import com.example.giziku.ui.theme.Register
import com.example.giziku.ui.theme.UpdateGrowthScreen
import com.example.giziku.util.CameraViewModel
import com.example.giziku.util.GrowthViewModel
import com.example.giziku.util.GrowthViewModelFactory
import com.example.giziku.util.UserViewModel
import com.example.giziku.util.UserViewModelFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.example.giziku.util.Nutrisi
import com.example.giziku.util.NutrisiViewModel
import com.example.giziku.util.NutrisiViewModelFactory


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val dao = AppDatabase.getDatabase(LocalContext.current).nutrisiDao()
            val navController = rememberNavController()
            val application = applicationContext as Application
            val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(application))
            val cameraViewModel: CameraViewModel = viewModel()
            val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
            var hasilAnalisis by remember { mutableStateOf<Nutrisi?>(null) }
            val nutrisiRepository = NutrisiRepository(dao)
            val nutrisiViewModel: NutrisiViewModel = viewModel(factory = NutrisiViewModelFactory(nutrisiRepository))
            val daoGrowth = AppDatabase.getDatabase(LocalContext.current).growthDao()
            val repository = GrowthRepository(daoGrowth)
            val growthViewModel: GrowthViewModel = viewModel(factory = GrowthViewModelFactory(repository))

            LaunchedEffect(key1 = cameraPermissionState.status) {
                if (!cameraPermissionState.status.isGranted) {
                    cameraPermissionState.launchPermissionRequest()
                }
            }

            NavHost(navController, startDestination = "awal") {
                composable("awal") { Awal(navController) }
                composable("login") { Login(navController) }
                composable("register") { Register(navController) }

                composable("home") { MedisHomeScreen(navController) } // Pastikan ini benar
                composable("edit_profile") { EditProfileScreen(navController) }
                composable("grafik") { GrafikScreen(navController) }
                composable("tambahsiswa") { GuruTambahSiswaScreen(navController) }
                composable("edukasi_gizi/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
                    EdukasiGiziMedis(navController, userId)
                }
                composable("edukasidetail/{edukasiId}") { backStackEntry ->
                    val edukasiId = backStackEntry.arguments?.getString("edukasiId")?.toIntOrNull()
                    val userViewModel: UserViewModel = viewModel(
                        factory = UserViewModelFactory(LocalContext.current.applicationContext as Application)
                    )

                    val edukasi by userViewModel.getEdukasiById(edukasiId ?: -1).observeAsState()

                }

                composable(
                    route = "gurulaporangizianak/{anakId}",
                    arguments = listOf(navArgument("anakId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val anakId = backStackEntry.arguments?.getString("anakId") ?: ""
                    GuruLaporanGizianakScreen(
                        anakId = anakId,
                        userViewModel = userViewModel,
                        navController
                    )
                }

                composable("homeorangtua") { OrangtuaHomeScreen(navController, growthViewModel, nutrisiViewModel) } // Pastikan ini benar
                composable("pendaftaran") { OrangtuaPendaftarananakScreen(navController) } // Pastikan ini benar
                composable("profileorangtua") { OrangtuaProfileScreen(navController) } // Pastikan ini benar
                composable("editprofileorangtua") { OrangtuaEditProfileScreen(navController) } // Pastikan ini benar
                composable("detailAnak") { OrangtuaEditProfileScreen(navController) } // Pastikan ini benar
                composable("kamera") {
                    CameraView(
                        viewModel = cameraViewModel,
                        nutrisiViewModel = nutrisiViewModel,
                        userId = userViewModel.currentUserId,
                    )
                }

                composable("updateTTBB/{anakId}") { backStackEntry ->
                    val result = backStackEntry.arguments?.getLong("anakId")
                    if (result != null) {
                        UpdateGrowthScreen(navController, anakId = result, viewModel = growthViewModel)
                    }
                }

                composable("tracker") {
                        NutriTracScreen(
                            nutrisiViewModel = nutrisiViewModel,
                            userId = userViewModel.currentUserId!!,
                            navController = navController
                        )
                }
                composable("resultScreen/{result}") { backStackEntry ->
                    val result = backStackEntry.arguments?.getString("result")
                    // Use the result here
                    Text(text = "Result: $result")
                }

                composable("detailanak/{id}") { backStackEntry ->
                    val anakId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        ?: return@composable
                    DetailAnakScreen(
                        anakId = anakId,
                        userViewModel = viewModel(), // atau pakai remember / inject sesuai kebutuhanmu
                        navController = navController, // Menambahkan navController di sini
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("editAnak/{anakId}") { backStackEntry ->
                    val anakId = backStackEntry.arguments?.getString("anakId")?.toIntOrNull()
                    if (anakId != null) {
                        EditAnakScreen(
                            anakId = anakId,
                            navController = navController,
                            userViewModel = userViewModel
                        )
                    }
                }
            }
        }
    }
}
