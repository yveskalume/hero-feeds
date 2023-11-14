package com.yveskalume.herofeeds.ui.home

import com.yveskalume.herofeeds.data.local.Creator

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(val creators: List<Creator>) : HomeUiState
}