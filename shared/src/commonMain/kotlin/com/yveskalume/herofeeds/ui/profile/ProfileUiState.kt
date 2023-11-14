package com.yveskalume.herofeeds.ui.profile

import com.yveskalume.herofeeds.data.local.Creator

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val creator: Creator) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}