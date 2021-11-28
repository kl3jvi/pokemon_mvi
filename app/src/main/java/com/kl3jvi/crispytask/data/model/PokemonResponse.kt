package com.kl3jvi.crispytask.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.uniflow.core.flow.data.UIState

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @field:Json(name = "results") val results: List<Pokemon>
) : UIState()