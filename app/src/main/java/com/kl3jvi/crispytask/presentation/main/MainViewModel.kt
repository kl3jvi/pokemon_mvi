package com.kl3jvi.crispytask.presentation.main


import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.kl3jvi.crispytask.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : AndroidDataFlow() {

    init {
        getPokemonList()
    }

    private fun getPokemonList() = action {
        getPokemonsUseCase().onEach { result ->
            when (result) {
                is Response.Success -> setState {
                    PokemonListState(
                        pokemons = result.data ?: emptyList()
                    )
                }

                is Response.Error -> setState {
                    PokemonListState(error = result.message ?: "An unexpected error occurred")
                }

                is Response.Loading -> setState { PokemonListState(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}