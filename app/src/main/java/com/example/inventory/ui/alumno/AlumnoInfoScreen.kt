package com.example.inventory.ui.alumno

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.inventory.model.Alumno
import com.example.inventory.ui.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlumnoInfoScreen(
    navController: NavController,
    viewModel: AlumnoInfoViewModel = viewModel(factory = AppViewModelProvider.Factory)
){

    var alumno by remember { mutableStateOf(Alumno()) }
    LaunchedEffect(key1 = "GET_ALUMNO") {
        alumno = viewModel.getAlumno()
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

        },
        bottomBar = {}
    ) {
        Column(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(32.dp))

            Column(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = alumno.nombre.substring(0,2),
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = alumno.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = alumno.carrera,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = alumno.especialidad)

            Spacer(modifier = Modifier.size(32.dp))
            Divider()
            Spacer(modifier = Modifier.size(32.dp))

            Text(text = "Semestre actual:")
            Text(
                text = alumno.semActual,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Creditos acumualdos:")
            Text(
                text = alumno.cdtosAcumulados.toString(),
                fontWeight = FontWeight.Bold
            )

            Text(text = "No. Control:")
            Text(
                text = alumno.matricula,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(32.dp))
            Divider()
            Spacer(modifier = Modifier.size(32.dp))


            Button(
                onClick = {

                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
                    .padding()
            ) {
                Text(text = "Cerrar sesion", modifier = Modifier.padding(PaddingValues(4.dp)))
            }
        }
    }
}