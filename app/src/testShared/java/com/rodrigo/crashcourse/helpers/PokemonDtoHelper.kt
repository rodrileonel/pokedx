package com.rodrigo.crashcourse.helpers

import com.rodrigo.crashcourse.data.dto.AbilitySlot
import com.rodrigo.crashcourse.data.dto.ArtworkSprites
import com.rodrigo.crashcourse.data.dto.MoveSlot
import com.rodrigo.crashcourse.data.dto.NamedAPIResource
import com.rodrigo.crashcourse.data.dto.OtherSprites
import com.rodrigo.crashcourse.data.dto.PokemonDto
import com.rodrigo.crashcourse.data.dto.Sprites
import com.rodrigo.crashcourse.data.dto.StatSlot
import com.rodrigo.crashcourse.data.dto.TypeSlot

object PokemonDtoHelper {
     fun fakePokemonDto(name: String = "pikachu"): PokemonDto {
        return PokemonDto(
            id = 25,
            name = name,
            base_experience = 112,
            height = 4,
            weight = 60,
            abilities = listOf(
                AbilitySlot(
                    ability = NamedAPIResource(
                        name = "static",
                        url = "https://pokeapi.co/api/v2/ability/9/"
                    ),
                    is_hidden = false,
                    slot = 1
                )
            ),
            types = listOf(
                TypeSlot(
                    slot = 1,
                    type = NamedAPIResource(
                        name = "electric",
                        url = "https://pokeapi.co/api/v2/type/13/"
                    )
                )
            ),
            sprites = Sprites(
                front_default = "front.png",
                back_default = "back.png",
                other = OtherSprites(
                    officialArtwork = ArtworkSprites(
                        front_default = "official.png"
                    )
                )
            ),
            moves = listOf(
                MoveSlot(
                    move = NamedAPIResource(
                        name = "thunder-punch",
                        url = "https://pokeapi.co/api/v2/move/9/"
                    )
                )
            ),
            stats = listOf(
                StatSlot(
                    base_stat = 35,
                    effort = 0,
                    stat = NamedAPIResource(
                        name = "hp",
                        url = "https://pokeapi.co/api/v2/stat/1/"
                    )
                )
            ),
            species = NamedAPIResource(
                name = "pikachu",
                url = "https://pokeapi.co/api/v2/pokemon-species/25/"
            )
        )
    }
}