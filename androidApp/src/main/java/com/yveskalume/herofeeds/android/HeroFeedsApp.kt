package com.yveskalume.herofeeds.android

import android.app.Application
import com.yveskalume.herofeeds.di.KoinWrapper
import com.yveskalume.herofeeds.ui.addcreator.AddCreatorViewModel
import com.yveskalume.herofeeds.ui.home.HomeViewModel
import com.yveskalume.herofeeds.ui.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class HeroFeedsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinWrapper.initKoin(
            vmModule = module {
                viewModel { HomeViewModel(get()) }
                viewModel { AddCreatorViewModel(get()) }
                viewModel { ProfileViewModel(get(),get()) }
            },
            appDeclaration = {
                androidLogger()
                androidContext(this@HeroFeedsApp)
            }
        )
    }
}