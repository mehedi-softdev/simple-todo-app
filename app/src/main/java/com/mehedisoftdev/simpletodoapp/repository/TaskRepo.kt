package com.mehedisoftdev.simpletodoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.mehedisoftdev.simpletodoapp.db.TaskDatabase
import com.mehedisoftdev.simpletodoapp.models.Task

class TaskRepo(private val taskDatabase: TaskDatabase) {
    val allTasks: LiveData<List<Task>> = taskDatabase.getTaskDao().getAllTasks()

    fun getAllTasksSorted(): LiveData<List<Task>> {
        return allTasks.map { tasks ->
            val completedTasks = tasks.filter { it.isComplete }
            val pendingTasks = tasks.filter { !it.isComplete }
            pendingTasks + completedTasks
        }
    }
    suspend fun addTask(task: Task) {
        taskDatabase.getTaskDao().insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDatabase.getTaskDao().updateTask(task)
    }


}