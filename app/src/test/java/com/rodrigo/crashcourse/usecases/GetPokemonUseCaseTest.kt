package com.rodrigo.crashcourse.usecases

import com.rodrigo.crashcourse.data.PokeRepository
import com.rodrigo.crashcourse.helpers.PokemonDtoHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest {

    private lateinit var repo: PokeRepository
    private lateinit var useCase: GetPokemonUseCase

    @Before
    fun setUp() {
        repo = mockk()
        useCase = GetPokemonUseCase(repo)
    }

    @Test
    fun when_repository_returns_success_use_case_returns_success() = runTest {
        val pokemon = PokemonDtoHelper.fakePokemonDto("pikachu")

        coEvery { repo.getPokemon("pikachu") } returns Result.success(pokemon)

        val result = useCase("pikachu")

        assertTrue(result.isSuccess)
        assertEquals("pikachu", result.getOrNull()?.name)

        coVerify(exactly = 1) { repo.getPokemon("pikachu") }
    }

    @Test
    fun when_repository_returns_failure_use_case_returns_failure() = runTest {
        val exception = RuntimeException("Network error")

        coEvery { repo.getPokemon("pikachu") } returns Result.failure(exception)

        val result = useCase("pikachu")

        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)

        coVerify(exactly = 1) { repo.getPokemon("pikachu") }
    }
}