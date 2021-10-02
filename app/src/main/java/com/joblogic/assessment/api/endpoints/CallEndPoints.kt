package com.joblogic.assessment.api.endpoints

import com.joblogic.assessment.api.models.JobLogicModelResponse
import retrofit2.http.GET

interface CallEndPoints {

    @GET("call")
    suspend fun getCallItems() : ArrayList<JobLogicModelResponse>

    @GET("buy")
    suspend fun getBuyItems() : ArrayList<JobLogicModelResponse>

}