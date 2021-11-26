package com.kl3jvi.crispytask.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "base_experience") val experience: Int,
    @field:Json(name = "types") val types: List<TypeResponse>,
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    
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
