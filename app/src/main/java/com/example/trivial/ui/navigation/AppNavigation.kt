package com.example.trivial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivial.ui.state.VM
import com.example.trivial.ui.screens.PlayScreen
import com.example.trivial.ui.screens.HomeScreen
import com.example.regalosnavidad.ui.navigation.AppScreens
import com.example.trivial.ui.screens.LastScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Aquí se obtiene el ViewModel a nivel de NavHost, lo que permite compartir el mismo ViewModel en todas las pantallas
    //así no se reincia el VM cuando navegamos mantiene las actualizaciones de los valores
    val viewModel: VM = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.HOME_SCREEN.name) {
        composable(AppScreens.HOME_SCREEN.name) {
            // Pasamos el ViewModel a la HomeScreen
            HomeScreen(
                navigateToPlayScreen = { navController.navigate(AppScreens.PLAY_SCREEN.name) },
                viewModel = viewModel // Aquí se pasa el mismo ViewModel
            )
        }

        composable(AppScreens.PLAY_SCREEN.name) {
            // Pasamos el ViewModel a la PlayScreen
            PlayScreen(
                navigateToLastScreen = {
                    navController.navigate(AppScreens.LAST_SCREEN.name) {
                        //popUpTo elimina la pila de pantallas hasta la pantalla que le indiquemos, marcando si incluída o no
                        popUpTo(AppScreens.HOME_SCREEN.name) { inclusive = true }
                    }
                },
                navigateBack = { navController.popBackStack() },
                viewModel = viewModel // Aquí también se pasa el mismo ViewModel de la pantalla anterior actualizado
            )
        }

        composable(AppScreens.LAST_SCREEN.name) {
            LastScreen(
                navigateToFirstScreen = {
                    navController.navigate(AppScreens.HOME_SCREEN.name) {
                        popUpTo(AppScreens.LAST_SCREEN.name) { inclusive = true }
                    }
                },
                viewModel = viewModel
            )
        }
    }
}
