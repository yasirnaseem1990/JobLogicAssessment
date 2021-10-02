package com.joblogic.assessment.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    call: suspend () -> T
): JobLogicAssessmentResponse<T> = withContext(Dispatchers.IO) {
    try {
        JobLogicAssessmentResponse.Success(call.invoke())
    } catch (exception: Exception) {
        JobLogicAssessmentResponse.Error(exception, exception.message)
    }
}

sealed class JobLogicAssessmentResponse<out R> {
    data class Success<out T>(val data: T) : JobLogicAssessmentResponse<T>()
    data class Error(val exception: Exception, val message: String? = null) :
        JobLogicAssessmentResponse<Nothing>()

}

val JobLogicAssessmentResponse<*>.succeeded
    get() = this is JobLogicAssessmentResponse.Success<*>

inline fun <T> JobLogicAssessmentResponse<T>.onSuccess(
    crossinline callback: (value: T) -> Unit
): JobLogicAssessmentResponse<T> {
    if (succeeded) {
        callback((this as JobLogicAssessmentResponse.Success<T>).data)
    }
    return this
}

inline fun <T> JobLogicAssessmentResponse<T>.onError(
    crossinline callback: (value: JobLogicAssessmentResponse.Error) -> Unit
): JobLogicAssessmentResponse<T> {
    if (!succeeded) {
        callback(this as JobLogicAssessmentResponse.Error)
    }
    return this
}

