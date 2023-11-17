package com.yveskalume.herofeeds.ui.profile

import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.data.repository.HashNodeRepository
import com.yveskalume.herofeeds.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

class ProfileViewModel(
    private val creatorRepository: CreatorRepository,
    private val hashNodeRepository: HashNodeRepository
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun getCreator(id: Long?) {
        if (id == null) {
            _uiState.value = _uiState.value.copy(errorMessage = "User not found")
            return
        }
        coroutineScope.launch {
            _uiState.emit(ProfileUiState(isCreatorLoading = true, errorMessage = null))
            try {
                creatorRepository.getCreator(id).collect { creator ->
                    _uiState.emit(
                        ProfileUiState(
                            isCreatorLoading = false,
                            creator = creator,
                            errorMessage = null
                        )
                    )
                    creator.hashnode?.let { getHashNodeBlogs(it) }
                }

            } catch (e: Exception) {
                logging().e { "Error : $e" }
                _uiState.emit(ProfileUiState(errorMessage = e.message ?: "An error occurred"))
            }
        }
    }

    private fun getHashNodeBlogs(host: String) {
        coroutineScope.launch {
            try {
                val posts = hashNodeRepository.getBlogPosts(host)
                _uiState.emit(
                    _uiState.value.copy(
                        isHashNodeLoading = false,
                        posts = _uiState.value.posts + posts,
                    )
                )
            } catch (e: Exception) {
                logging().e { "Error : $e" }
            }
        }
    }
}