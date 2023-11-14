package com.yveskalume.herofeeds.ui

import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel {
    actual open val coroutineScope: CoroutineScope
        get() = TODO("Not yet implemented")
}