package com.anushka.roommvvmcrudapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.demo.repository.TaskRepository
import com.android.demo.viewmodel.TaskViewModel
import java.lang.IllegalArgumentException

class TaskViewModelFactory(
    private val repository: TaskRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")

    }

}