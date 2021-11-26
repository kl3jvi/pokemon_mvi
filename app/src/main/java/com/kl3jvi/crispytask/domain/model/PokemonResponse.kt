package com.kl3jvi.crispytask.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.uniflow.core.flow.data.UIState

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val results: List<Pokemon>
): UIState()