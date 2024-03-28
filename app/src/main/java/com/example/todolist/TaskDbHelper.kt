package com.example.todolist
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.UUID


class TaskDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Database version and name
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "task.db"
    }

    // Database functions
    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (
                ${TaskContract.TaskEntry.COLUMN_ID} TEXT PRIMARY KEY,
                ${TaskContract.TaskEntry.COLUMN_NAME} TEXT NOT NULL,
                `${TaskContract.TaskEntry.COLUMN_DESC}` TEXT,
                ${TaskContract.TaskEntry.COLUMN_COMPLETED} INTEGER NOT NULL DEFAULT 0
            );
        """.trimIndent()

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Not needed for now
    }

    fun insertTask(task: TaskModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_ID, task.id.toString())
            put(TaskContract.TaskEntry.COLUMN_NAME, task.name)
            put(TaskContract.TaskEntry.COLUMN_DESC, task.desc)
            put(TaskContract.TaskEntry.COLUMN_COMPLETED, if (task.completed) 1 else 0)
        }

        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)
        db.close()
    }

    fun updateTask(task: TaskModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_NAME, task.name)
            put(TaskContract.TaskEntry.COLUMN_DESC, task.desc)
            put(TaskContract.TaskEntry.COLUMN_COMPLETED, if (task.completed) 1 else 0)
        }

        db.update(
            TaskContract.TaskEntry.TABLE_NAME,
            values,
            "${TaskContract.TaskEntry.COLUMN_ID} = ?",
            arrayOf(task.id.toString())
        )
        db.close()
    }

    fun deleteTask(task: TaskModel) {
        val db = writableDatabase
        db.delete(
            TaskContract.TaskEntry.TABLE_NAME,
            "${TaskContract.TaskEntry.COLUMN_ID} = ?",
            arrayOf(task.id.toString())
        )
        db.close()
    }
    @SuppressLint("Range")
    fun getAllTasks(): List<TaskModel> {
        val tasks = mutableListOf<TaskModel>()

        val cursor = readableDatabase.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val id = UUID.fromString(cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_ID)))
            val name = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME))
            val desc = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESC))
            val completed = cursor.getInt(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_COMPLETED)) == 1

            tasks.add(TaskModel(name, desc, completed, id))
        }

        cursor.close()
        return tasks
    }

}
