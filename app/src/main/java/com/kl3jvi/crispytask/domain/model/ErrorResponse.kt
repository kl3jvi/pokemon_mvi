package com.kl3jvi.crispytask.domain.model

import io.uniflow.core.flow.data.UIState

data class ErrorResponse(
    val code: Int,
    val message: String?
): UIState()