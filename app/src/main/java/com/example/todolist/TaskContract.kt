package com.example.todolist

import android.provider.BaseColumns

object TaskContract {
    // Define table and column names
    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "task_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESC = "desc"
        const val COLUMN_COMPLETED = "completed" // New column for completion status
    }
}
