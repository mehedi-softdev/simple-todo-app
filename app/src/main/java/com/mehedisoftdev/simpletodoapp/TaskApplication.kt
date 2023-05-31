package com.mehedisoftdev.simpletodoapp

import android.app.Application
import com.mehedisoftdev.simpletodoapp.db.TaskDatabase
import com.mehedisoftdev.simpletodoapp.repository.TaskRepo

class TaskApplication: Application() {
    private lateinit var taskRepo: TaskRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        // creating instance of db for first time
        val taskDatabase = TaskDatabase.getInstance(applicationContext)
        taskRepo = TaskRepo(taskDatabase) // initialize the repository object
    }
}