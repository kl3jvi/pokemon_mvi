package com.kl3jvi.crispytask.domain.states

import io.uniflow.core.flow.data.UIState

sealed class PokemonResponseState<T>(val data: T? = null, val message: String? = null) : UIState() {
    class Success<T>(data: T?) : PokemonResponseState<T>(data)
    class Error<T>(message: String?) : PokemonResponseState<T>(message = message)
    class Loading<T>(data: T? = null) : PokemonResponseState<T>(data)
}

