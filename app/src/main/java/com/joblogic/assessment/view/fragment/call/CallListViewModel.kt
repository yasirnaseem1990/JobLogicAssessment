package com.joblogic.assessment.view.fragment.call

import androidx.lifecycle.*
import com.joblogic.assessment.api.models.JobLogicModelResponse
import com.joblogic.assessment.api.onError
import com.joblogic.assessment.api.onSuccess
import com.joblogic.assessment.repository.CallRepository
import com.swvl.assessmenttest.utils.SingleLiveEvent
import com.swvl.assessmenttest.utils.asLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

class CallListViewModel(
    private val callRepository: CallRepository
) : ViewModel(), LifecycleObserver {

    private val errorOccurred = SingleLiveEvent<String>()

    private val _callList = MutableLiveData<List<JobLogicModelResponse>>()
    val guyList = _callList.asLiveData()

    private val _showLoading = MutableLiveData<Boolean>()
    private val showLoading = _showLoading.asLiveData()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        getCallList()
    }

    private fun getCallList() {
        viewModelScope.launch {
            _showLoading.value = true
            callRepository.getCallList()
                .onSuccess {
                    it.let { _callList.value = it }
                }
                .onError { error ->
                    Timber.e(error.message)
                    error.message.let {
                        errorOccurred.value = it
                    }
                }
            _showLoading.value = false

        }
    }


    /**
     * Call this method inside the onViewCreated() when got error
     */
    fun getErrorResult(): LiveData<String> = errorOccurred

    fun getShowLoadingResult(): LiveData<Boolean> = showLoading
}