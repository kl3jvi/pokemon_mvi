package com.kl3jvi.crispytask.data.model

import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.uniflow.core.flow.data.UIState

@JsonClass(generateAdapter = true)
data class PokemonInfoDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "base_experience") val experience: Int,
    @field:Json(name = "types") val types: List<TypeResponse>,
) : UIState() {

    fun getIdString(): String = String.format("#%03d", id)

    @JsonClass(generateAdapter = true)
    data class TypeResponse(
        @field:Json(name = "slot") val slot: Int,
        @field:Json(name = "type") val type: Type
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        @field:Json(name = "name") val name: String
    )
}

fun PokemonInfoDto.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = getIdString(),
        name = name,
        height = height,
        weight = weight,
        experience = experience,
        types = types
    )
}
