package com.beeeam.mvipractice

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class MainViewModel @Inject constructor() : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun updateCount(count: Int) = intent {
        if (state.count > count) {
            toastMsg("Count Minus, Count: $count")
        } else {
            toastMsg("Count Add, Count: $count")
        }
        reduce { state.copy(count = count) }
    }

    private fun toastMsg(msg: String) = intent {
        postSideEffect(MainSideEffect.ToastMsg(msg))
    }
}
