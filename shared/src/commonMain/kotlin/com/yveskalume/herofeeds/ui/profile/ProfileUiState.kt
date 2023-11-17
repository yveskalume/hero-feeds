package com.yveskalume.herofeeds.ui.profile

import com.yveskalume.herofeeds.data.local.Creator
import com.yveskalume.herofeeds.data.model.IPost

data class ProfileUiState(
    val creator: Creator? = null,
    val posts: List<IPost> = emptyList(),
    val isCreatorLoading: Boolean = true,
    val errorMessage: String? = null,
    val isHashNodeLoading: Boolean = true,
    val isMediumLoading: Boolean = true,
    val isTwitterLoading: Boolean = true,
) {
    val arePostsLoading: Boolean
        get() = isHashNodeLoading && isMediumLoading && isTwitterLoading
}