package com.rodrigo.crashcourse.data.dto


data class PokesDto(
    val results:List<PokeDto>,
)

data class PokeDto(
    val name:String,
    val url:String
)