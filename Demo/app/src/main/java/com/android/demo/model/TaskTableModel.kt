package com.android.demo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_data_table")
data class TaskTableModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var id: Int,

    @ColumnInfo(name = "task_name")
    var name: String,

    @ColumnInfo(name = "task_email")
    var email: String,

    var selected:Boolean = false

)