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
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { AddCreatorViewModel(get()) }
    factory { ProfileViewModel(get(),get()) }
}

val dataModule = module {
    single { Database(get()) }

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


