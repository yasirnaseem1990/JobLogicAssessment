package com.joblogic.assessment.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joblogic.assessment.api.db.converter.Converters
import com.joblogic.assessment.api.db.converter.TodoTypeConverter
import com.joblogic.assessment.api.models.JobLogicModelResponse


@Database(entities = [JobLogicModelResponse::class], version = 1, exportSchema = false)

@TypeConverters(
    Converters::class,
    TodoTypeConverter::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoListDao: TodoListDao
}