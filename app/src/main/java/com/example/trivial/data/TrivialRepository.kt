package com.example.trivial.data

import com.example.trivial.model.QuestionApiResponse
import com.example.trivial.network.ApiService

interface TrivialRepositoryInterface {
    suspend fun getApiQuestions(): List<QuestionApiResponse.QuestionApi>
}

//TrivialRepository implementa la interfaz TrivialRepositoryInterface y utiliza la instancia del servicio web para obtener los datos del servidor.
class TrivialRepository(private val apiService: ApiService) : TrivialRepositoryInterface {
    override suspend fun getApiQuestions(): List<QuestionApiResponse.QuestionApi> {
        return try {
            // Accede a la propiedad "results" del objeto QuestionApiResponse
            apiService.getApiQuestions().results
        } catch (e: Exception) {
            //Devolvemos una lista vacía en caso de error
            emptyList()
        }
    }
}