package com.joblogic.assessment.di

import com.joblogic.assessment.view.activity.launcher.LauncherViewModel
import com.joblogic.assessment.view.fragment.buy.BuyListViewModel
import com.joblogic.assessment.view.fragment.call.CallListViewModel
import com.joblogic.assessment.view.fragment.sell.SellListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        LauncherViewModel( )
    }

    viewModel {
        BuyListViewModel( get() )
    }

    viewModel {
        CallListViewModel( get() )
    }

    viewModel {
        SellListViewModel( get() )
    }
}