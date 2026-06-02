package com.example.medreminder

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.medreminder.presentation.MainViewModel
import com.example.medreminder.ui.navigation.AppNavGraph
import com.example.medreminder.ui.theme.MedreminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.startDestination.value == null
        }

        setContent {
            MedreminderTheme {
                val startDestination by mainViewModel.startDestination.collectAsState()
                val navController = rememberNavController()

                startDestination?.let {
                    AppNavGraph(navController = navController, startDestination = it)
                }
            }
        }
    }
}