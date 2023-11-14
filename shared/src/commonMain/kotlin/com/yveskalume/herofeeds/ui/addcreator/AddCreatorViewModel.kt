package com.yveskalume.herofeeds.ui.addcreator

import com.yveskalume.herofeeds.data.local.Creator
import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddCreatorViewModel(private val creatorRepository: CreatorRepository) : BaseViewModel() {


    private val _uiState: MutableStateFlow<AddCreatorState> = MutableStateFlow(AddCreatorState.Idle)
    val uiState: StateFlow<AddCreatorState> = _uiState.asStateFlow()

    fun addCreator(name: String, bio: String, twitter: String, hashnode: String, medium: String) {
        coroutineScope.launch {
            _uiState.emit(AddCreatorState.Loading)
            try {
                val creator = Creator(
                    id = 0, name = name,
                    bio = bio,
                    twitter = twitter,
                    hashnode = hashnode,
                    medium = medium,
                    photo = null
                )

                creatorRepository.insert(creator)
                _uiState.emit(AddCreatorState.Success)
            } catch (e: Exception) {
                _uiState.emit(AddCreatorState.Error(e.message ?: "An error occured"))
            }
        }
    }
}