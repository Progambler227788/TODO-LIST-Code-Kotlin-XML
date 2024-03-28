package com.example.todolist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ViewholderBinding

class TaskItemViewHolder(
    private val context: Context,
    private val binding: ViewholderBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{

    // Binding Tasks on Recycler View
    fun bindTaskItem(taskItem: TaskModel)
    {
        binding.nameTB.text = taskItem.name
        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }
        binding.deleteButton.setOnClickListener{
            clickListener.deleteTaskItem(taskItem)
        }
    }
}