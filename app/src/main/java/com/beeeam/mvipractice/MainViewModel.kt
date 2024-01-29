package com.beeeam.mvipractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class MainViewModel @Inject constructor() : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun loading() = intent {
        showLoadingScreen()
        delay(2000L)
        hideLoadingScreen()
    }

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

    private fun showLoadingScreen() = intent { reduce { state.copy(isLoading = true) } }
    private fun hideLoadingScreen() = intent { reduce { state.copy(isLoading = false) } }
}
