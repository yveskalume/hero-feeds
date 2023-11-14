package com.yveskalume.herofeeds.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel : ViewModel() {
    actual open val coroutineScope: CoroutineScope
        get() = viewModelScope
}