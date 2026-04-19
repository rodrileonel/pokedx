package com.rodrigo.crashcourse.data.dto

data class PokemonDto(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val abilities: List<AbilitySlot>,
    val types: List<TypeSlot>,
    val sprites: Sprites,
    val moves: List<MoveSlot>,
    val stats: List<StatSlot>,
    val species: NamedAPIResource
)

data class AbilitySlot(
    val ability: NamedAPIResource,
    val is_hidden: Boolean,
    val slot: Int
)

data class TypeSlot(
    val slot: Int,
    val type: NamedAPIResource
)

data class MoveSlot(
    val move: NamedAPIResource
)

data class StatSlot(
    val base_stat: Int,
    val effort: Int,
    val stat: NamedAPIResource
)

data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val other: OtherSprites?
)

data class OtherSprites(
    val officialArtwork: ArtworkSprites?
)

data class ArtworkSprites(
    val front_default: String?
)

data class NamedAPIResource(
    val name: String,
    val url: String
)
