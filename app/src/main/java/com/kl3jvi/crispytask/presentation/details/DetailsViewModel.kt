package com.kl3jvi.crispytask.presentation.details

import com.kl3jvi.crispytask.data.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: DetailsRepository
) : AndroidDataFlow() {

    private var _name: String = ""
    val pokemonName get() = _name

    // Method with return type to get Name for adapter :)
    fun updatePokemonName(name: String) {
        _name = name
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