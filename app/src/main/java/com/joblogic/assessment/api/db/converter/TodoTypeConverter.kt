package com.joblogic.assessment.api.db.converter


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.joblogic.assessment.api.models.JobLogicModelResponse


class TodoTypeConverter {
    @TypeConverter
    fun stringToJobLogicModelResponse(json: String): List<JobLogicModelResponse> {
        val type = object : TypeToken<List<JobLogicModelResponse>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun jobLogicModelResponseToString(list: List<JobLogicModelResponse>): String {
        val type = object : TypeToken<List<JobLogicModelResponse>>() {}.type
        return  Gson().toJson(list, type)
    }
}
