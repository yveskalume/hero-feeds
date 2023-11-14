package com.yveskalume.herofeeds.ui.home

import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.ui.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val creatorRepository: CreatorRepository) : BaseViewModel() {

    val uiState: StateFlow<HomeUiState> = creatorRepository.getAll().map { data ->
        HomeUiState.Success(data)
    }.catch<HomeUiState> {
        emit(HomeUiState.Error(it.message ?: "An error occured"))
    }.stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(5000),
        HomeUiState.Loading
    )
}