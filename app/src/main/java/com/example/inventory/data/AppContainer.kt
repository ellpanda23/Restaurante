/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import android.content.Context
import com.example.inventory.network.repository.SicenetApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val networkSicenetRepository: SicenetRepository
    val offlineSicenetRepository: SicenetRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    private val BASE_URL =
        "https://sicenet.surguanajuato.tecnm.mx/"

    private val interceptor = CookiesInterceptor()

    private val cliente = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL).client(cliente)
        .build()

    private val retrofitService : SicenetApiService by lazy {
        retrofit.create(SicenetApiService::class.java)
    }
    /**
     * Implementation for [SicenetRepository]
     */
    override val networkSicenetRepository: NetworkSicenetRepository by lazy {
        NetworkSicenetRepository(retrofitService)
    }

    override val offlineSicenetRepository: OfflineSicenetRepository by lazy {
        OfflineSicenetRepository(SicenetDatabase.getDatabase(context).alumnoDao())
    }
}

class CookiesInterceptor: Interceptor {
    // Variable que almacena las cookies
    private var cookies: List<String> = emptyList()
    // Método para establecer las cookies
    fun setCookies(cookies: List<String>) {
        this.cookies = cookies
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        // Agregar las cookies al encabezado de la solicitud
        if (cookies.isNotEmpty()) {
            val cookiesHeader = StringBuilder()
            for (cookie in cookies) {
                if (cookiesHeader.isNotEmpty()) {
                    cookiesHeader.append("; ")
                }
                cookiesHeader.append(cookie)
            }
            request = request.newBuilder()
                .header("Cookie", cookiesHeader.toString())
                .build()
        }

        val response = chain.proceed(request)

        // Almacenar las cookies de la respuesta para futuras solicitudes
        val receivedCookies = response.headers("Set-Cookie")
        if (receivedCookies.isNotEmpty()) {
            setCookies(receivedCookies)
        }

        return response
    }
}