package com.mdirusso.dailypulse.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class ScopeViewModel : ViewModel() {
    actual val scope = viewModelScope
}
