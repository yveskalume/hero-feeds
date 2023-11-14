package com.yveskalume.herofeeds.ui.profile

import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val creatorRepository: CreatorRepository) : BaseViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun getCreator(id: Long?) {
        if (id == null) {
            _uiState.value = ProfileUiState.Error("Creator does not exit")
            return
        }
        coroutineScope.launch {
            _uiState.emit(ProfileUiState.Loading)
            try {
                creatorRepository.getCreator(id).collect { creator ->
                    _uiState.emit(ProfileUiState.Success(creator))
                }
            } catch (e: Exception) {
                _uiState.emit(ProfileUiState.Error(e.message ?: "An error occured"))
            }
        }
    }
}