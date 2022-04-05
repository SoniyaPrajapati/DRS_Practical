package com.android.demo.room

import androidx.room.*
import com.android.demo.model.TaskTableModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert
    suspend fun insertSubscriber(taskTableModel: TaskTableModel) : Long

    @Update
    suspend fun updateSubscriber(taskTableModel: TaskTableModel) : Int

    @Delete
    suspend fun deleteSubscriber(taskTableModel: TaskTableModel) : Int

    @Query("DELETE FROM task_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM task_data_table")
    fun getAllSubscribers():Flow<List<TaskTableModel>>
}