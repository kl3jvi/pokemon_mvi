package com.kl3jvi.crispytask.presentation.details

import com.kl3jvi.crispytask.data.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: DetailsRepository
) : AndroidDataFlow() {

    fun updatePokemonName(name: String) {
        getPokemonInfo(name)
    }

    private fun getPokemonInfo(pokemonName: String) = action {
        try {
            val pokemonResponse = repo.getPokemonDetails(pokemonName = pokemonName)
            setState { pokemonResponse }
        } catch (e: Exception) {
            setState { it }
        }
    }
}