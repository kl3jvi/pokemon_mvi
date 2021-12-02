package com.kl3jvi.crispytask.data.model

import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "base_experience") val experience: Int,
    @field:Json(name = "types") val types: List<TypeResponseDto>,
) {

    fun getIdString(): String = String.format("#%03d", id)

    @JsonClass(generateAdapter = true)
    data class TypeResponseDto(
        @field:Json(name = "slot") val slot: Int,
        @field:Json(name = "type") val type: TypeDto
    )

    fun TypeResponseDto.toTypeResponse(): PokemonInfo.TypeResponse {
        return PokemonInfo.TypeResponse(
            slot = slot,
            type = type
        )
    }

    @JsonClass(generateAdapter = true)
    data class TypeDto(
        @field:Json(name = "name") val name: String
    )

    fun TypeDto.toType(): PokemonInfo.Type {
        return PokemonInfo.Type(name = name)
    }
}

fun PokemonInfoDto.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = getIdString(),
        name = name,
        height = height,
        weight = weight,
        experience = experience,
        types = types.map { it.toTypeResponse() }
    )
}


