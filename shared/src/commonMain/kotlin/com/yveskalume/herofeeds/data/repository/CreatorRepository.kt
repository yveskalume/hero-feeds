package com.yveskalume.herofeeds.data.repository

import com.yveskalume.herofeeds.data.datasource.local.CreatorLocalDataSource
import com.yveskalume.herofeeds.data.local.Creator
import kotlinx.coroutines.flow.Flow

class CreatorRepository(private val localDataSource: CreatorLocalDataSource) {

    fun getAll(): Flow<List<Creator>> = localDataSource.getAll()

    suspend fun insert(creator: Creator) = localDataSource.insert(creator)
}