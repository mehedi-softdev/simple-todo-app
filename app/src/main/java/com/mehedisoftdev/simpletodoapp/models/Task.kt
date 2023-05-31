package com.mehedisoftdev.simpletodoapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskName: String,
    val endDate: String,
    var isComplete: Boolean
)