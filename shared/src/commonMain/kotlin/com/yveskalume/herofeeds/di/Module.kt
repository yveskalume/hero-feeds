package com.yveskalume.herofeeds.di

import com.apollographql.apollo3.ApolloClient
import com.yveskalume.herofeeds.data.datasource.local.CreatorLocalDataSource
import com.yveskalume.herofeeds.data.datasource.remote.HashNodeRemoteDataSource
import com.yveskalume.herofeeds.data.local.Database
import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.data.repository.HashNodeRepository
import com.yveskalume.herofeeds.ui.addcreator.AddCreatorViewModel
import com.yveskalume.herofeeds.ui.home.HomeViewModel
import com.yveskalume.herofeeds.ui.profile.ProfileViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { AddCreatorViewModel(get()) }
    factory { ProfileViewModel(get(), get()) }
}

val dataModule = module {
    single { Database(get()) }

    single { provideKtoClient() }

    factory { CreatorRepository(get()) }
    factory { CreatorLocalDataSource(get()) }

    factory { HashNodeRepository(get()) }
    factory { HashNodeRemoteDataSource(get()) }

    factory {
        ApolloClient.Builder()
            .serverUrl("https://gql.hashnode.com/")
            .build()
    }
}

private fun provideKtoClient() : HttpClient {
    return HttpClient {
        expectSuccess = false
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}


