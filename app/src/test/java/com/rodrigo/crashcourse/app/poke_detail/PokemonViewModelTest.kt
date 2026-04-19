package com.rodrigo.crashcourse.app.poke_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.rodrigo.crashcourse.Screen
import com.rodrigo.crashcourse.helpers.PokemonDtoHelper
import com.rodrigo.crashcourse.usecases.GetPokemonUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getPokemonUseCase: GetPokemonUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPokemonUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_argument_exists_and_use_case_succeeds_state_is_success() = runTest {
        val pokemon = PokemonDtoHelper.fakePokemonDto("pikachu")

        coEvery { getPokemonUseCase("pikachu") } returns Result.success(pokemon)

        val savedStateHandle = SavedStateHandle(
            mapOf(Screen.PokeDetail.ARG to "pikachu")
        )

        val viewModel = PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase,
            savedStateHandle = savedStateHandle
        )

        advanceUntilIdle()

        assertTrue(viewModel.pokemonState is PokemonState.Success)
        assertEquals(
            "pikachu",
            (viewModel.pokemonState as PokemonState.Success).poke.name
        )

        coVerify(exactly = 1) { getPokemonUseCase("pikachu") }
    }

    @Test
    fun when_argument_exists_and_use_case_fails_state_is_error() = runTest {
        coEvery { getPokemonUseCase("pikachu") } returns Result.failure(
            RuntimeException("Network error")
        )

        val savedStateHandle = SavedStateHandle(
            mapOf(Screen.PokeDetail.ARG to "pikachu")
        )

        val viewModel = PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase,
            savedStateHandle = savedStateHandle
        )

        advanceUntilIdle()

        assertTrue(viewModel.pokemonState is PokemonState.Error)
        assertEquals(
            "Network error",
            (viewModel.pokemonState as PokemonState.Error).message
        )

        coVerify(exactly = 1) { getPokemonUseCase("pikachu") }
    }

    @Test
    fun when_argument_is_missing_state_is_error() = runTest {
        val savedStateHandle = SavedStateHandle()

        val viewModel = PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase,
            savedStateHandle = savedStateHandle
        )

        advanceUntilIdle()

        assertTrue(viewModel.pokemonState is PokemonState.Error)
        assertEquals(
            "No se recibió el nombre del Pokémon",
            (viewModel.pokemonState as PokemonState.Error).message
        )

        coVerify(exactly = 0) { getPokemonUseCase(any()) }
    }
}