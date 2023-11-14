package com.yveskalume.herofeeds.di

import com.yveskalume.herofeeds.data.datasource.local.CreatorLocalDataSource
import com.yveskalume.herofeeds.data.local.Database
import com.yveskalume.herofeeds.data.repository.CreatorRepository
import com.yveskalume.herofeeds.ui.addcreator.AddCreatorViewModel
import com.yveskalume.herofeeds.ui.home.HomeViewModel
import com.yveskalume.herofeeds.ui.profile.ProfileViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { AddCreatorViewModel(get()) }
    factory { ProfileViewModel(get()) }
}

val dataModule = module {
    single { Database(get()) }

    factory { CreatorRepository(get()) }
    factory { CreatorLocalDataSource(get()) }
}


