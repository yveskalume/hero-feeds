package com.yveskalume.herofeeds.ui.addcreator

sealed interface AddCreatorState {
    data object Idle : AddCreatorState
    data object Loading : AddCreatorState
    data class Error(val message: String) : AddCreatorState
    data object Success : AddCreatorState
}