package com.android.demo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.demo.model.TaskTableModel


@Database(entities = [TaskTableModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDAO: TaskDAO

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}

