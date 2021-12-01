package com.kl3jvi.crispytask.domain.model

import com.kl3jvi.crispytask.data.model.PokemonInfoDto

data class PokemonInfo(
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val experience: Int,
    val types: List<PokemonInfoDto.TypeResponse>,
)
