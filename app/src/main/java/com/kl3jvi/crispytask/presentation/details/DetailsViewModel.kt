package com.kl3jvi.crispytask.presentation.details

import android.util.Log
import com.kl3jvi.crispytask.data.model.PokemonInfoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository
) : AndroidDataFlow() {
    fun updatePokemonName(name: String) {
        getPokemonInfo(name)
    }

    private fun getPokemonInfo(pokemonName: String) = action {
        try {
            val pokemonResponse =
                pokemonDetailsRepository.getPokemonDetails(
                    pokemonName = pokemonName,
                    onError = { error -> Log.e("Error", error ?: "") })
            setState { TestStates.ResponseState(pokemonResponse) }
        } catch (e: Exception) {
            setState { it }
        }
    }

    open class TestStates : UIState(){

        object LoadingWeather : TestStates()
        data class ResponseState(val flow: Flow<PokemonInfoDto>) : TestStates()
    }
}