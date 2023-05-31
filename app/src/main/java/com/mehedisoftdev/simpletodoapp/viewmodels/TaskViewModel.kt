package com.mehedisoftdev.simpletodoapp.viewmodels

import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedisoftdev.simpletodoapp.models.Task
import com.mehedisoftdev.simpletodoapp.repository.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(private val taskRepo: TaskRepo): ViewModel()
{
    var taskLiveData: LiveData<List<Task>> = taskRepo.getAllTasksSorted()

    init {
       loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = taskRepo.getAllTasksSorted()
            taskLiveData = tasks
        }
    }



    fun addTask(task: Task) {
       viewModelScope.launch(Dispatchers.IO) {
           taskRepo.addTask(task)
       }
    }

    // update task
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.updateTask(task)
        }
    }



}