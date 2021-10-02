package com.joblogic.assessment.di

import android.app.Application
import androidx.room.Room
import com.joblogic.assessment.api.db.TodoDatabase
import com.joblogic.assessment.api.db.TodoListDao
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, "ItemToSell.db")
            .createFromAsset("databases/jl-demo.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideMoviesDao(database: TodoDatabase): TodoListDao {
        return database.todoListDao
    }

    single { provideDatabase(get()) }
    single { provideMoviesDao(get()) }


}
