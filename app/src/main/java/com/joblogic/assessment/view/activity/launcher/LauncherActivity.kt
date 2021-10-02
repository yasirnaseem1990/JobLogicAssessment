package com.joblogic.assessment.view.activity.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joblogic.assessment.R
import com.joblogic.assessment.view.activity.MainActivity
import com.swvl.assessmenttest.utils.finishAndNavigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : AppCompatActivity(R.layout.activity_launcher) {

    private val viewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)

        viewModel.navigateToHome.observe(this) {
            finishAndNavigateTo(MainActivity::class.java)
        }
    }
}