package com.mdirusso.dailypulse.screens

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object Articles : Screens()
    @Serializable
    data object About : Screens()
    @Serializable
    data object Sources : Screens()
}
