package com.mehedisoftdev.simpletodoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehedisoftdev.simpletodoapp.adapters.TaskAdapter
import com.mehedisoftdev.simpletodoapp.databinding.FragmentTaskBinding
import com.mehedisoftdev.simpletodoapp.viewmodels.TaskViewModel
import com.mehedisoftdev.simpletodoapp.viewmodels.TaskViewModelFactory


class TaskFragment : Fragment() {
    private var _binding : FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)

        binding.taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.taskRecyclerView.setHasFixedSize(true)

        val taskRepo = (requireActivity().application as TaskApplication).taskRepo
        taskViewModel = ViewModelProvider(requireActivity(), TaskViewModelFactory(taskRepo))[TaskViewModel::class.java]


        taskAdapter = TaskAdapter(taskViewModel)
        binding.taskRecyclerView.adapter = taskAdapter

        // Observing tasks
        taskViewModel.taskLiveData.observe(viewLifecycleOwner, Observer { tasks ->
            taskAdapter.submitList(tasks)
        })

        taskViewModel.loadTasks()


        // add task event handler
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
        }

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        taskViewModel.loadTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}