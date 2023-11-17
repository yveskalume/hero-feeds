package com.yveskalume.herofeeds.data.model.hashnode

import com.yveskalume.herofeeds.data.model.IPost

data class HashNodeRemotePost(
    override val id: String,
    val title: String,
    val url: String,
    val coverImage: String?,
    val publishedAt: Any,
) : IPost
