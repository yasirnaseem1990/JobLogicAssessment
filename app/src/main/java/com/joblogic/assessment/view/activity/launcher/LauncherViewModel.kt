package com.joblogic.assessment.view.activity.launcher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.swvl.assessmenttest.utils.SingleLiveEvent

class LauncherViewModel : ViewModel(), LifecycleObserver {

    val navigateToHome = SingleLiveEvent<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart() {
       navigateToHome.call()
    }

}