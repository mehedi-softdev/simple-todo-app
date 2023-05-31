package com.mehedisoftdev.simpletodoapp.db

import androidx.lifecycle.LiveData
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

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task")
    abstract fun getAllTasks(): LiveData<List<Task>>

}