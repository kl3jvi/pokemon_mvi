package com.kl3jvi.crispytask.data.network

import com.kl3jvi.crispytask.data.model.PokemonInfo
import com.kl3jvi.crispytask.data.model.PokemonResponse
import javax.inject.Inject

class PokemonApiClient @Inject constructor(
    private val pokemonService: PokemonService
) {
    suspend fun fetchPokemonList(): PokemonResponse =
        pokemonService.fetchPokemonList(
            limit = SIZE,
            offset = SIZE
        )

    suspend fun fetchPokemonDetails(name: String): PokemonInfo =
        pokemonService.fetchPokemonInfo(name = name)

    companion object {
        private const val SIZE = 20
    }
}
