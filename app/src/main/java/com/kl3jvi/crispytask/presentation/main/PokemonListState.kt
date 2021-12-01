package com.kl3jvi.crispytask.presentation.main

import com.kl3jvi.crispytask.domain.model.Pokemon
import io.uniflow.core.flow.data.UIState

open class PokemonListState : UIState() {
    object PokemonsAreLoading : PokemonListState()
    data class PokemonsRetrieved(val pokemons: List<Pokemon> = emptyList()) : PokemonListState()
    data class PokemonsRetrievedError(val message: String) : PokemonListState()
}