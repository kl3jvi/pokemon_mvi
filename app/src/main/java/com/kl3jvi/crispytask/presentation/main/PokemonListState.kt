package com.kl3jvi.crispytask.presentation.main

import com.kl3jvi.crispytask.domain.model.Pokemon

sealed interface PokemonsUiState {
    object PokemonsAreLoading : PokemonsUiState
    data class PokemonsRetrieved(val pokemons: List<Pokemon>) : PokemonsUiState
    data class PokemonsRetrievedError(val error: Throwable?) : PokemonsUiState
}