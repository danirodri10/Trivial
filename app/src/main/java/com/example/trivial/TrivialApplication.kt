package com.example.trivial

import android.app.Application
import com.example.trivial.data.DataContainer
import com.example.trivial.data.DataContainerImpl


// la clase TrivialApplication extiende de Application y adjunta el contenedor de dependencias a la aplicación en la función onCreate()
class TrivialApplication : Application() {
    lateinit var container: DataContainer
    override fun onCreate() {
        super.onCreate()
        container = DataContainerImpl
    }
}