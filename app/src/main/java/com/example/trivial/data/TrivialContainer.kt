package com.example.trivial.data

import com.example.trivial.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

//url a la que vamos a hacer la llamada
private const val BASE_URL = "https://lotrapi.co"

//interfaz que proporciona una instancia del repositorio de datos en la aplicación
interface DataContainer {
    val provideTrivialRepository: TrivialRepository
}

//DataContainerImpl implementa la interfaz DataContainer y proporciona una instancia del repositorio de datos utilizando la instancia del servicio web.
object DataContainerImpl : DataContainer {
    // Crea una instancia de Retrofit con la URL base y el convertidor de Scalars
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    // Crea una instancia del servicio web a partir de la interfaz de servicio web y la instancia de Retrofit
    private val apiService by lazy { retrofit.create(ApiService::class.java) }
    private val trivialRepository = TrivialRepository(apiService)

    override val provideTrivialRepository: TrivialRepository by lazy { trivialRepository }

}