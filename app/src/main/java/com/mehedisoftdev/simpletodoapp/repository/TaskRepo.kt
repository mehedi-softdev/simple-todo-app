package com.mehedisoftdev.simpletodoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mehedisoftdev.simpletodoapp.db.TaskDatabase
import com.mehedisoftdev.simpletodoapp.models.Task

class TaskRepo(private val taskDatabase: TaskDatabase) {
    private val taskLiveMutableData = MutableLiveData<List<Task>>()
    val taskLiveData: LiveData<List<Task>> get() = taskLiveMutableData

    // data is fetching only from local cache
    suspend fun getAllTask() { // fetch all the tasks
        val task = taskDatabase.getTaskDao().getAllTask()
        taskLiveMutableData.postValue(task)
    }


}