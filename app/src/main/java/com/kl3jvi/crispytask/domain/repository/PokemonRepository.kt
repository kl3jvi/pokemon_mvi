package com.kl3jvi.crispytask.domain.repository

import com.kl3jvi.crispytask.data.model.PokemonDto
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import com.kl3jvi.crispytask.data.model.PokemonResponseDto
import kotlinx.coroutines.flow.Flow

/** Repository is an interface for configuring base repository classes. */
interface PokemonRepository {

    fun getPokemons(page:Int): Flow<List<PokemonDto>>

    suspend fun getPokemonByName(pokemonName: String): PokemonInfoDto
}