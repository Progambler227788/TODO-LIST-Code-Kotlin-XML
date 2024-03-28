package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application)
{   // Data holder
    var taskItems = MutableLiveData<MutableList<TaskModel>>()
    private val dbHelper = TaskDbHelper(application.applicationContext) // database SQLITE

    // Initializer
    init {
        taskItems.value = mutableListOf()
        loadTasksFromDatabase()
    }
    // Methods
    fun addTaskItem(newTask: TaskModel)
    {   dbHelper.insertTask(newTask)
        loadTasksFromDatabase()
    }

    fun updateTaskItem(id: UUID, name: String, desc: String)
    {
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        dbHelper.updateTask(task)
        loadTasksFromDatabase()
    }
    fun setCompleted(taskItem: TaskModel) {
        taskItem.completed = !taskItem.completed
        // Update the list and notify observers
        dbHelper.updateTask(taskItem)
        loadTasksFromDatabase()
    }
    fun deleteTaskItem(taskItem: TaskModel) {
        // Delete task from the database
        dbHelper.deleteTask(taskItem)
        loadTasksFromDatabase()

    }
    private fun  loadTasksFromDatabase() {
        val tasks = dbHelper.getAllTasks()
         taskItems.postValue(tasks.toMutableList())
    }


}