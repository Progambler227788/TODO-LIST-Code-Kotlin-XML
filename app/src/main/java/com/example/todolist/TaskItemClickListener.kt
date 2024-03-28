package com.example.todolist

interface TaskItemClickListener
{    // Methods to edit task, complete task, and delete task
    fun editTaskItem(taskItem: TaskModel)
    fun completeTaskItem(taskItem: TaskModel)
    fun deleteTaskItem(taskItem: TaskModel)
}