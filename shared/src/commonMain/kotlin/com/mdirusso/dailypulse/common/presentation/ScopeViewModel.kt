package com.mdirusso.dailypulse.common.presentation

import kotlinx.coroutines.CoroutineScope

expect open class ScopeViewModel() {
    val scope: CoroutineScope
}
