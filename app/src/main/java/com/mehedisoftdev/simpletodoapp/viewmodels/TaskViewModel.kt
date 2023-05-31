package com.mehedisoftdev.simpletodoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedisoftdev.simpletodoapp.models.Task
import com.mehedisoftdev.simpletodoapp.repository.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepo: TaskRepo): ViewModel()
{
    val taskLiveData: LiveData<List<Task>> = taskRepo.taskLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.getAllTask()
        }
    }


}