package com.yveskalume.herofeeds.data.repository

import com.yveskalume.herofeeds.data.datasource.remote.HashNodeRemoteDataSource

class HashNodeRepository(private val remoteDataSource: HashNodeRemoteDataSource) {

    suspend fun getBlogPosts(host: String) = remoteDataSource.getBlogPosts(host)
}