package com.rodrigo.crashcourse.app.poke_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.crashcourse.Screen
import com.rodrigo.crashcourse.data.dto.PokemonDto
import com.rodrigo.crashcourse.usecases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var pokemonState by mutableStateOf<PokemonState>(PokemonState.Idle)
        private set

    //private val pokeName: String = savedStateHandle["pokeName"] ?: ""
    private val pokeName = savedStateHandle.get<String>(Screen.PokeDetail.ARG)

    init {
        if (!pokeName.isNullOrBlank()) {
            getPokemon(pokeName)
        } else {
            pokemonState = PokemonState.Error("No se recibió el nombre del Pokémon")
        }
    }

    private fun getPokemon(pokeName:String){
        viewModelScope.launch {
            pokemonState = PokemonState.Loading
            getPokemonUseCase(pokeName)
                .onSuccess { pokemonState = PokemonState.Success(it) }
                .onFailure { pokemonState = PokemonState.Error(it.message ?: "Error")}
        }
    }
}

sealed class PokemonState{
    object Idle: PokemonState()
    object Loading: PokemonState()
    data class Success(val poke: PokemonDto): PokemonState()
    data class Error(val message: String): PokemonState()
}