package com.yveskalume.herofeeds.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

object KoinWrapper {
    // viewModelModule will be created using koin's viewModel { } function on Android
    fun initKoin(vmModule: Module = viewModelModule, appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            modules(listOf(platformModule, dataModule, vmModule))
            appDeclaration()
        }
    }

//    For iOS
    fun initKoin() {
        startKoin {
            modules(listOf(platformModule, dataModule, viewModelModule))
        }
    }
}