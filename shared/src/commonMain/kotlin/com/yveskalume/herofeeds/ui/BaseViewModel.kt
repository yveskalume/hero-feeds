package com.yveskalume.herofeeds.ui

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    open val coroutineScope: CoroutineScope
}