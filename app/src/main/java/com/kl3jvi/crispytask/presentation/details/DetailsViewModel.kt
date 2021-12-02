package com.kl3jvi.crispytask.presentation.details

import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.use_case.get_pokemon.GetPokemonUseCase
import com.kl3jvi.crispytask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : AndroidDataFlow() {


    fun updatePokemonName(name: String) {
        getPokemonInfo(name)
    }

    private fun getPokemonInfo(pokemonName: String) = action {
        getPokemonUseCase(pokemonName = pokemonName).onEach { result ->
            when (result) {
                is ResponseState.Success -> setState { PokemonDetailsState.PokemonRetrieved(result.data) }
                is ResponseState.Error -> setState {
                    PokemonDetailsState.PokemonRetrievedError(
                        result.message ?: "An unexpected error occurred"
                    )
                }
                is ResponseState.Loading -> setState { PokemonDetailsState.PokemonIsLoading }
            }
        }.launchIn(viewModelScope)
    }


}