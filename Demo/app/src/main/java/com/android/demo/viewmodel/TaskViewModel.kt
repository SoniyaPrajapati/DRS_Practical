package com.android.demo.viewmodel

import android.util.Patterns
import androidx.lifecycle.*
import com.android.demo.Event
import com.android.demo.model.TaskTableModel
import com.android.demo.repository.TaskRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    private var isUpdateOrDelete = false
    private lateinit var taskTableModelToUpdateOrDelete: TaskTableModel
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDelete(taskTableModel: TaskTableModel) {
        inputName.value = taskTableModel.name
        inputEmail.value = taskTableModel.email
        isUpdateOrDelete = true
        taskTableModelToUpdateOrDelete = taskTableModel
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun saveOrUpdate() {
        if (inputName.value == null) {
            statusMessage.value = Event("Please enter name")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Please enter a correct email address")
        } else {
            if (isUpdateOrDelete) {
                taskTableModelToUpdateOrDelete.name = inputName.value!!
                taskTableModelToUpdateOrDelete.email = inputEmail.value!!
                updateSubscriber(taskTableModelToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insertSubscriber(TaskTableModel(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
            }
        }
    }

    private fun insertSubscriber(taskTableModel: TaskTableModel) = viewModelScope.launch {
        val newRowId = repository.insert(taskTableModel)
        if (newRowId > -1) {
            statusMessage.value = Event("Task Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }


    private fun updateSubscriber(taskTableModel: TaskTableModel) = viewModelScope.launch {
        val noOfRows = repository.update(taskTableModel)
        if (noOfRows > 0) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun getSavedSubscribers() = liveData {
        repository.subscribers.collect {
            emit(it)
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            deleteSubscriber(taskTableModelToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun deleteSubscriber(taskTableModel: TaskTableModel) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(taskTableModel)
        if (noOfRowsDeleted > 0) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Task Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }
}