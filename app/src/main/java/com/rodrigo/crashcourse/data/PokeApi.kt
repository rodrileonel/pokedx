package com.rodrigo.crashcourse.data

import com.rodrigo.crashcourse.data.dto.PokemonDto
import com.rodrigo.crashcourse.data.dto.PokesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokes(): PokesDto

    @GET("pokemon/{pokeName}")
    suspend fun getPokemon(@Path("pokeName") pokeName:String): PokemonDto
}
