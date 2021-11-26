package com.kl3jvi.crispytask.data.repository

import com.kl3jvi.crispytask.data.network.PokemonApiClient
import com.kl3jvi.crispytask.data.network.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonClient: PokemonApiClient
) {
    suspend fun getPokemonList() = pokemonClient.fetchPokemonList()
    suspend fun getPokemonInfo(pokemonName: String) = pokemonClient.fetchPokemonInfo(pokemonName)
}