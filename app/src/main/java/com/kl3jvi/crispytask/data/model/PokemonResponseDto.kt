package com.kl3jvi.crispytask.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonResponseDto(
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "next")
    val next: String,
    @field:Json(name = "previous")
    val previous: String,
    @field:Json(name = "results")
    val results: List<PokemonDto>
)