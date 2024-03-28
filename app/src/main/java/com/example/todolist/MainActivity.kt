package com.example.todolist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener
{   // Variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    // Functions
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[TaskViewModel::class.java]

        binding.newTaskButton.setOnClickListener {
            FragmentTask(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }
    // Setting Recycler View
    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }
    // Edit Task Item
    override fun editTaskItem(taskItem: TaskModel)
    {
        FragmentTask(taskItem).show(supportFragmentManager,"newTaskTag")
    }
    // Complete Task Item
    override fun completeTaskItem(taskItem: TaskModel)
    {
        taskViewModel.setCompleted(taskItem)
    }
    // Delete Task Item
    override fun deleteTaskItem(taskItem: TaskModel) {
       taskViewModel.deleteTaskItem(taskItem)
        setRecyclerView()  // Refresh
    }
}







