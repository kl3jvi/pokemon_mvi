package com.kl3jvi.crispytask.data.model

import com.kl3jvi.crispytask.domain.model.PokemonResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.uniflow.core.flow.data.UIState

@JsonClass(generateAdapter = true)
data class PokemonResponseDto(
    @field:Json(name = "results") val results: List<PokemonDto>
) : UIState() {
    fun PokemonResponseDto.toPokemonResponse(): PokemonResponse {
        return PokemonResponse(
            results = results
        )
    }
}