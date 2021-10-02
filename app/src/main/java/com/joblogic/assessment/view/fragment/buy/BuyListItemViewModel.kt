package com.joblogic.assessment.view.fragment.buy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.joblogic.assessment.api.models.JobLogicModelResponse

class BuyListItemViewModel : ViewModel() {

    private val callList = MutableLiveData<JobLogicModelResponse>()

    val name = callList.map { it.name }

    val price = callList.map { it.price.toString() }

    val quantity = callList.map { it.quantity.toString()}

    val type = callList.map { it.type.toString()}

    fun bind(item: JobLogicModelResponse) {
        callList.value = item
    }

}