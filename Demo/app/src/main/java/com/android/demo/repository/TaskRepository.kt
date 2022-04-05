package com.android.demo.repository

import com.android.demo.model.TaskTableModel
import com.android.demo.room.TaskDAO

class TaskRepository(private val dao: TaskDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(taskTableModel: TaskTableModel): Long {
        return dao.insertSubscriber(taskTableModel)
    }

    suspend fun update(taskTableModel: TaskTableModel): Int {
        return dao.updateSubscriber(taskTableModel)
    }

    suspend fun delete(taskTableModel: TaskTableModel): Int {
        return dao.deleteSubscriber(taskTableModel)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}