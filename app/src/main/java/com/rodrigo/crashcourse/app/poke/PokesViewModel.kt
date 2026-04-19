package com.rodrigo.crashcourse.app.poke

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.crashcourse.data.dto.PokeDto
import com.rodrigo.crashcourse.usecases.GetPokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokesViewModel @Inject constructor(
    private val getPokesUseCase: GetPokesUseCase
): ViewModel() {
    var pokesState by mutableStateOf<PokeState>(PokeState.Idle)
        private set

    init {
        getPokes()
    }

    fun getPokes(){
        viewModelScope.launch {
            pokesState = PokeState.Loading
            getPokesUseCase()
                .onSuccess { pokesState = PokeState.Success(it) }
                .onFailure { pokesState = PokeState.Error(it.message ?: "Error")}
        }
    }
}

sealed class PokeState{
    object Idle: PokeState()
    object Loading: PokeState()
    data class Success(val pokes: List<PokeDto>): PokeState()
    data class Error(val message: String): PokeState()
}