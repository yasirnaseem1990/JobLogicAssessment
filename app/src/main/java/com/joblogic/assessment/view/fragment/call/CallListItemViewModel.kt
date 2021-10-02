package com.joblogic.assessment.view.fragment.call

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.joblogic.assessment.api.models.JobLogicModelResponse

class CallListItemViewModel : ViewModel() {

    private val buyList = MutableLiveData<JobLogicModelResponse>()


    val name = buyList.map { it.name }

    val number = buyList.map { it.number }

    fun bind(item: JobLogicModelResponse) {
        buyList.value = item
    }

}