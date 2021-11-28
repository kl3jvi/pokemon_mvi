package com.kl3jvi.crispytask.presentation.main


import com.kl3jvi.crispytask.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepository
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