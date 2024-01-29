package com.beeeam.mvipractice

data class MainState(
    val count: Int = 0,
)

sealed interface MainSideEffect
