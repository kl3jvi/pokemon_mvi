package com.kl3jvi.crispytask.domain.model

import com.kl3jvi.crispytask.data.model.PokemonDto

data class PokemonResponse(
    val results: List<PokemonDto>
)
