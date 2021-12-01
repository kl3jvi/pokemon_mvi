package com.kl3jvi.crispytask.domain.repository

import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonInfoDto

/** Repository is an interface for configuring base repository classes. */
interface PokemonRepository {

    suspend fun getPokemons(): List<PokemonDto>

    suspend fun getPokemonByName(pokemonName: String): PokemonInfoDto
}