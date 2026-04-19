package com.rodrigo.crashcourse

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigo.crashcourse.app.main.MainScreen
import com.rodrigo.crashcourse.app.main.MainViewModel
import com.rodrigo.crashcourse.app.poke.PokesScreen
import com.rodrigo.crashcourse.app.poke.PokesViewModel
import com.rodrigo.crashcourse.app.poke_detail.PokemonScreen
import com.rodrigo.crashcourse.app.poke_detail.PokemonViewModel

@Composable
fun AppNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val vm: MainViewModel = hiltViewModel()
            MainScreen(
                onNavigateToDetail = { navController.navigate(Screen.Pokes.route) },
                count = vm.count,
                text = vm.text,
                texts = vm.texts,
                onTextChanged = vm::onTextChanged,
                addText = vm::addText,
            )
        }
        composable(Screen.Pokes.route) {
            val vm: PokesViewModel = hiltViewModel()
            PokesScreen (
                pokesState = vm.pokesState,
                onBack = { navController.popBackStack() },
                onNavigateToDetail = { name ->
                    navController.navigate(
                        //Names.POKEDETAIL.createPokeDetailRoute(Uri.encode(name))
                        Screen.PokeDetail.createRoute(name)
                    )
                }
            )
        }
        composable(Screen.PokeDetail.route) {
            val vm: PokemonViewModel = hiltViewModel()
            PokemonScreen (
                pokemonState = vm.pokemonState,
                onBack = { navController.popBackStack() }
            )
        }
    }

}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}


// forma base
enum class Names(val route:String) {
    HOME("home"),
    POKES("pokes"),
    POKEDETAIL("pokeDetail/{pokeName}");

    fun createPokeDetailRoute(pokeName: String): String {
        return "pokeDetail/$pokeName"
    }
}

//forma pro
sealed class Screen(val route:String){
    object Home: Screen("home")
    object Pokes: Screen("pokes")
    object PokeDetail: Screen("pokeDetail/{pokeName}") {
        const val ARG = "pokeName"
        fun createRoute(name:String) = "pokeDetail/$name"
    }
}
