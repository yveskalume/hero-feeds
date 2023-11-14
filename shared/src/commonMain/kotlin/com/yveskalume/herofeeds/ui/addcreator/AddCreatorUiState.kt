package com.yveskalume.herofeeds.ui.addcreator

sealed interface AddCreatorUiState {
    data object Idle : AddCreatorUiState
    data object Loading : AddCreatorUiState
    data class Error(val message: String) : AddCreatorUiState
    data object Success : AddCreatorUiState
}