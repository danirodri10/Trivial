package com.example.trivial

import android.app.Application
import com.example.trivial.data.TrivialContainer
import com.example.trivial.data.DataContainerImpl


/**
 * Application class for the Trivia app
 *
 * Initializes the AppContainer
 *
 * Esta clase es la encargada de inicializar el contenedor de dependencias
 *
 * @see TrivialContainer
 *
 * OJO: No olvides declarar esta clase en el AndroidManifest.xml para ello agrega la siguiente linea:
 *
 * <application
 *    android:name=".TriviaApplication"
 *    ...
 *    >
 *    ...
 *    </application>
 *
 *    Esta linea debe ir dentro del tag <application> en el archivo AndroidManifest.xml
 */


class TrivialApp : Application() {
    lateinit var container: TrivialContainer

    override fun onCreate() {
        super.onCreate()
        container = DataContainerImpl()
    }
}
