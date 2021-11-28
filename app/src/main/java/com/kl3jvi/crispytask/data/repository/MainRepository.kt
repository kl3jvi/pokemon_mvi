package com.kl3jvi.crispytask.data.repository

import com.kl3jvi.crispytask.data.network.PokemonApiClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonClient: PokemonApiClient
) {
    suspend fun getPokemonList() = pokemonClient.fetchPokemonList()
}