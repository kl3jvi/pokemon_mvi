package com.kl3jvi.crispytask.data.network

import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.domain.model.PokemonResponse
import javax.inject.Inject

class PokemonApiClient @Inject constructor(
    private val pokedexService: PokemonService
) {

    suspend fun fetchPokemonList(): List<PokemonResponse> =
        pokedexService.fetchPokemonList(
            limit = SIZE,
            offset = SIZE
        )

    suspend fun fetchPokemonInfo(
        name: String
    ): List<PokemonInfo> =
        pokedexService.fetchPokemonInfo(
            name = name
        )

    companion object {
        private const val SIZE = 20
    }
}
