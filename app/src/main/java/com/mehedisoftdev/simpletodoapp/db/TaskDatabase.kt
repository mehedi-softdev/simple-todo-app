package com.mehedisoftdev.simpletodoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehedisoftdev.simpletodoapp.models.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun getTaskDao() : TaskDao

    companion object {
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            if(instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        TaskDatabase::class.java,
                        "todo"
                    ).build()

                }
            }
            return instance!!
        }
    }
}