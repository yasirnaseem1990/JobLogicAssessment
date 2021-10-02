package com.joblogic.assessment.repository

import com.joblogic.assessment.api.JobLogicAssessmentResponse
import com.joblogic.assessment.api.db.TodoListDao
import com.joblogic.assessment.api.endpoints.CallEndPoints
import com.joblogic.assessment.api.models.JobLogicModelResponse
import com.joblogic.assessment.api.safeApiCall


class CallRepository(
    private val api: CallEndPoints,
    private val todoListDao: TodoListDao
) {
    suspend fun getCallList(): JobLogicAssessmentResponse<ArrayList<JobLogicModelResponse>> {
        return safeApiCall {
            api.getCallItems()
        }
    }

    suspend fun getBuyList(): JobLogicAssessmentResponse<ArrayList<JobLogicModelResponse>> {
        return safeApiCall {
            api.getBuyItems()
        }
    }

    suspend fun getSellList(): JobLogicAssessmentResponse<List<JobLogicModelResponse>> {
        return safeApiCall {
            todoListDao.findAll()
        }
    }
}