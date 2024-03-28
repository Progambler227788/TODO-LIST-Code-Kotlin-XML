package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentTask(private var taskItem: TaskModel?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskViewModel: TaskViewModel

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null)
        {
            binding.taskCreationTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.nameTB.text = editable.newEditable(taskItem!!.name)
            binding.descTB.text = editable.newEditable(taskItem!!.desc)
        }
        else
        {
            binding.taskCreationTitle.text = "Create a Task"
        }

        taskViewModel = ViewModelProvider(
            activity,
            ViewModelProvider.AndroidViewModelFactory(activity.application)
        )[TaskViewModel::class.java]

        binding.saveTaskBT.setOnClickListener {
            saveAction()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction()
    {
        val name = binding.nameTB.text.toString()
        val desc = binding.descTB.text.toString()
        if(taskItem == null)
        {
            val newTask = TaskModel(name,desc)
            taskViewModel.addTaskItem(newTask)
        }
        else
        {
            taskViewModel.updateTaskItem(taskItem!!.id, name, desc)
        }
        binding.nameTB.setText("")
        binding.descTB.setText("")
        dismiss()
    }

}








