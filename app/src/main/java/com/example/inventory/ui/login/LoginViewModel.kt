package com.example.inventory.ui.login

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.InventoryApplication
import com.example.inventory.data.SicenetRepository
import com.example.inventory.model.Alumno
import com.example.inventory.ui.NetworkUtils

class LoginViewModel(
    private val networkSicenetRepository: SicenetRepository,
    private val offlineSicenetRepository: SicenetRepository,
    private val application: Application
) : ViewModel(){

    var matricula by mutableStateOf("s20120157")
    var password by mutableStateOf("8Cj%_e3")

    // Actualizar matricula
    fun updateMatricula(value: String){
        matricula = value
    }

    // Actulziar password
    fun updatePassword(value: String){
        password = value
    }

    fun validate(): Boolean = matricula != "" && password != ""

    suspend fun getAccceso(m: String = matricula, p: String = password): Boolean
    {
        return if(isConnected(application))
            return networkSicenetRepository.getAcceso(m, p)
        else true
    }
    suspend fun getAlumno(): Alumno = networkSicenetRepository.getAlumno()

    suspend fun insertAlumno(alumno: Alumno) = offlineSicenetRepository.insertAlumno(alumno)

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

}