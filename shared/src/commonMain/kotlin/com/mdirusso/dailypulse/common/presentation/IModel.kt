package com.mdirusso.dailypulse.common.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IModel<STATE, INTENT, EFFECT> {

    val state: StateFlow<STATE>
    val effect: Flow<EFFECT>

    fun dispatchIntent(intent: INTENT)
}
