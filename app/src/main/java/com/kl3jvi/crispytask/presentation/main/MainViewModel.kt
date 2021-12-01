package com.kl3jvi.crispytask.presentation.main


import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PokeRepo
) : AndroidDataFlow() {

    init {
        getPokemonList()
    }

    private fun getPokemonList() = action {
        try {
            val pokemonResponse = repo.getPokemonList()
            setState { pokemonResponse }
        } catch (e: Exception) {
            setState { it }
        }
    }
}