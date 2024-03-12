package com.example.inventory.ui.carga

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.inventory.ui.AppViewModelProvider


@Composable
fun CargaAcademicaScreen(
    navController: NavController,
    viewModel: CargaAcademicaViewModel = viewModel(factory = AppViewModelProvider.Factory)
)
{
    LaunchedEffect(key1 = "CargaAcademica") {
        viewModel.getCarga()
    }
}