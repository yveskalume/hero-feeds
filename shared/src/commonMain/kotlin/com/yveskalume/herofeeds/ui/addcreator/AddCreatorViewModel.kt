package com.yveskalume.herofeeds.ui.addcreator

import com.yveskalume.herofeeds.data.local.Creator
import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddCreatorViewModel(private val creatorRepository: CreatorRepository) : BaseViewModel() {


    private val _uiState: MutableStateFlow<AddCreatorUiState> = MutableStateFlow(AddCreatorUiState.Idle)
    val uiState: StateFlow<AddCreatorUiState> = _uiState.asStateFlow()

    fun addCreator(name: String, bio: String, twitter: String, hashnode: String, medium: String) {
        coroutineScope.launch {
            _uiState.emit(AddCreatorUiState.Loading)
            try {
                val creator = Creator(
                    id = 0, name = name,
                    bio = bio,
                    twitter = twitter.ifBlank { null },
                    hashnode = hashnode.ifBlank { null },
                    medium = medium.ifBlank { null },
                    photo = null
                )

                creatorRepository.insert(creator)
                _uiState.emit(AddCreatorUiState.Success)
            } catch (e: Exception) {
                _uiState.emit(AddCreatorUiState.Error(e.message ?: "An error occured"))
            }
        }
    }
}