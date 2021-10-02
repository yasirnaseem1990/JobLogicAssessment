package com.joblogic.assessment.api.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ItemToShell")
@Parcelize
data class JobLogicModelResponse
    (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int? = null,
    val number: String
) : Parcelable
