package com.kl3jvi.crispytask.presentation.main


import com.kl3jvi.crispytask.data.repository.PokemonRepository
import com.kl3jvi.crispytask.domain.model.PokemonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PokemonRepository
) : AndroidDataFlow(defaultState = UIState.Empty) {

    init {
        getPokemonList()
    }

    fun getPokemonList() = action {
        val pokemonList = repo.getPokemonList().results
        // return a new state
        setState { PokemonResponse(pokemonList) }
    }
}