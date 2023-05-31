package com.mehedisoftdev.simpletodoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mehedisoftdev.simpletodoapp.repository.TaskRepo

class TaskViewModelFactory(private val taskRepo: TaskRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(taskRepo) as T
    }
}