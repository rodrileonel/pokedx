package com.rodrigo.crashcourse.usecases

import com.rodrigo.crashcourse.data.PokeRepository
import com.rodrigo.crashcourse.data.dto.PokemonDto
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repo: PokeRepository){
    suspend operator fun invoke(pokeName:String): Result<PokemonDto>{
        return repo.getPokemon(pokeName)
    }
}