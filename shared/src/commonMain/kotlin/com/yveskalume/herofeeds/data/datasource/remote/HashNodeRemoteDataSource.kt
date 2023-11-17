package com.yveskalume.herofeeds.data.datasource.remote

import com.apollographql.apollo3.ApolloClient
import com.yveskalume.herofeeds.data.model.hashnode.HashNodeRemotePost
import hashnode.GetPublicationQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class HashNodeRemoteDataSource(private val apolloClient: ApolloClient) {

    suspend fun getBlogPosts(host: String): List<HashNodeRemotePost> {
        return withContext(Dispatchers.IO) {
            val response = apolloClient.query(GetPublicationQuery(host, 10)).execute()
            val data = response.dataAssertNoErrors
            data.publication?.posts?.edges?.map {
                HashNodeRemotePost(
                    id = it.node.id,
                    title = it.node.title,
                    url = it.node.url,
                    coverImage = it.node.coverImage?.url,
                    it.node.publishedAt
                )
            } ?: emptyList()
        }
    }
}