package com.mdirusso.dailypulse.common.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState, EFFECT : ViewEffect>(
    val initialState: STATE,
) : ScopeViewModel(), IModel<STATE, INTENT, EFFECT> {

    protected val mState = MutableStateFlow(initialState)
    protected val mEffect = MutableSharedFlow<EFFECT>()

    override val state: StateFlow<STATE>
        get() = mState

    override val effect: Flow<EFFECT>
        get() = mEffect.asSharedFlow()

    override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        scope.launch { block() }
    }

    fun changeState(state: STATE) {
        launchOnUI {
            mState.update { state }
        }
    }

    fun triggerEffect(effect: EFFECT) {
        launchOnUI {
            mEffect.emit(effect)
        }
    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)
}