package com.mehedisoftdev.simpletodoapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mehedisoftdev.simpletodoapp.models.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task")
    suspend fun getAllTask(): List<Task>

}