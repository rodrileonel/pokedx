package com.rodrigo.crashcourse.usecases

import com.rodrigo.crashcourse.data.PokeRepository
import com.rodrigo.crashcourse.data.dto.PokeDto
import javax.inject.Inject

class GetPokesUseCase @Inject constructor(private val repo: PokeRepository){
    suspend operator fun invoke(): Result<List<PokeDto>>{
        val res = repo.getPokes()
        return res.map { it.results }
    }
}