package com.example.trivial.network

import com.example.trivial.model.LotrCharacters
import retrofit2.http.GET

// Define la interfaz de servicio web con las operaciones disponibles
interface ApiService {
    @GET("characters")
    suspend fun getLotrCharacters(): List<LotrCharacters>
}