package com.yveskalume.herofeeds.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.yveskalume.herofeeds.data.local.Creator
import com.yveskalume.herofeeds.data.local.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CreatorLocalDataSource(private val database: Database) {

    suspend fun insert(creator: Creator) {
        withContext(Dispatchers.IO) {
            database.creatorQueries.add(
                name = creator.name,
                bio = creator.bio,
                photo = creator.photo,
                hashnode = creator.hashnode,
                medium = creator.medium
            )
        }
    }

    fun getAll(): Flow<List<Creator>> {
        return database.creatorQueries.getAll().asFlow().mapToList(Dispatchers.IO)
    }

    fun getCreator(id: Long): Flow<Creator> {
        return database.creatorQueries.getCreatorById(id).asFlow().mapToOne(Dispatchers.IO)
    }
}