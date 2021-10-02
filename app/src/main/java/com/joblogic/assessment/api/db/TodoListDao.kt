package com.joblogic.assessment.api.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joblogic.assessment.api.models.JobLogicModelResponse

@Dao
interface TodoListDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<JobLogicModelResponse>)

    @Query("SELECT * FROM ItemToShell")
    fun findAll(): List<JobLogicModelResponse>


}