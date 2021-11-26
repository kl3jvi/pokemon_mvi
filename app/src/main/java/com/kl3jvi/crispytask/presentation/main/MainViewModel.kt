package com.kl3jvi.crispytask.presentation.main


import com.kl3jvi.crispytask.data.repository.PokemonRepository
import com.kl3jvi.crispytask.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PokemonRepository
) : AndroidDataFlow(defaultState = UIState.Empty) {

    fun getPokemonList() = action {
        val pokemonList = repo.getPokemonList()
        // return a new state

    }

}