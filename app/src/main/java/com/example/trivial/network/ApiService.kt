package com.example.trivial.network

import com.example.trivial.model.QuestionApiResponse
import retrofit2.http.GET

// Define la interfaz de servicio web con las operaciones disponibles
interface ApiService {
    @GET("api.php?amount=10&type=multiple")
    suspend fun getApiQuestions(): List<QuestionApiResponse.QuestionApi>
}