package com.example.todolist

import android.content.Context
import androidx.core.content.ContextCompat
import java.util.*

// Model Class
class TaskModel (
    var name: String,
    var desc: String,
    var completed: Boolean = false,
    var id: UUID = UUID.randomUUID()
)
{   // Methods of Model
    private fun isCompleted() = completed
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}