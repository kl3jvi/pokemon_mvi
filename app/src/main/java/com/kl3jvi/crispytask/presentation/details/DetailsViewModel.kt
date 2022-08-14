package com.kl3jvi.crispytask.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.crispytask.domain.model.PokemonInfo
import com.kl3jvi.crispytask.domain.use_case.get_pokemon.GetPokemonUseCase
import com.kl3jvi.crispytask.utils.Result
import com.kl3jvi.crispytask.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {


    fun updatePokemonName(name: String) {
        pokemonName.value = name
    }

    private val pokemonName = MutableStateFlow("")

    val pokemonUiState = pokemonName.flatMapLatest { name ->
        getPokemonUseCase(pokemonName = name).asResult().map {
            when (it) {
                is Result.Error -> PokemonUiState.Error(it.exception)
                Result.Loading -> PokemonUiState.Loading
                is Result.Success -> PokemonUiState.Success(it.data)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PokemonUiState.Loading
    )


}

sealed interface PokemonUiState {
    object Loading : PokemonUiState
    data class Success(val data: PokemonInfo) : PokemonUiState
    data class Error(val throwable: Throwable?) : PokemonUiState

}