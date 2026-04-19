package com.rodrigo.crashcourse.data

import com.rodrigo.crashcourse.data.dto.PokemonDto
import com.rodrigo.crashcourse.data.dto.PokesDto
import javax.inject.Inject

interface PokeRepository {
    suspend fun getPokes(): Result<PokesDto>
    suspend fun getPokemon(pokeName:String): Result<PokemonDto>
}

class PokeRepositoryImpl @Inject constructor(
    private val api: PokeApi
): PokeRepository {
    override suspend fun getPokes(): Result<PokesDto> {
        return try {
            val response = api.getPokes()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getPokemon(pokeName: String): Result<PokemonDto> {
        return try {
            val response = api.getPokemon(pokeName)
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}