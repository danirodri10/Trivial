package com.example.trivial.data

import com.example.trivial.model.LotrCharacters
import com.example.trivial.network.ApiService

interface TrivialRepositoryInterface {
    suspend fun getLotrCharacters(): List<LotrCharacters>
}

//TrivialRepository implementa la interfaz TrivialRepositoryInterface y utiliza la instancia del servicio web para obtener los datos del servidor.
class TrivialRepository(private val apiService: ApiService) : TrivialRepositoryInterface {
    override suspend fun getLotrCharacters(): List<LotrCharacters> {
        return apiService.getLotrCharacters()
    }
}