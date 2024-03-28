package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ViewholderBinding


class TaskItemAdapter(
    private val taskItems: List<TaskModel>,
    private val clickListener: TaskItemClickListener
): RecyclerView.Adapter<TaskItemViewHolder>()
{   // Creating Item View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ViewholderBinding.inflate(from, parent, false)
        return TaskItemViewHolder(parent.context, binding, clickListener)
    }
    // Binding Data
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }
    // Get total items
    override fun getItemCount(): Int {
        return taskItems.size
    }
}