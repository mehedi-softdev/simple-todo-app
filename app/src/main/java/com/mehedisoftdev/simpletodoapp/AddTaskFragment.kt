package com.mehedisoftdev.simpletodoapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mehedisoftdev.simpletodoapp.databinding.FragmentAddBinding
import com.mehedisoftdev.simpletodoapp.models.Task
import com.mehedisoftdev.simpletodoapp.utils.Constants
import com.mehedisoftdev.simpletodoapp.viewmodels.TaskViewModel
import com.mehedisoftdev.simpletodoapp.viewmodels.TaskViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        // init view model
        val taskRepo = (requireActivity().application as TaskApplication).taskRepo
        taskViewModel = ViewModelProvider(requireActivity(),
        TaskViewModelFactory(taskRepo))[TaskViewModel::class.java]
        calendar = Calendar.getInstance()

        // for task end endDate picker
        binding.selectDateButton.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireActivity(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Update the selected endDate
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(selectedYear, selectedMonth, selectedDay)
                    val formattedDate = Constants.formatDate(selectedDate.time)
                    binding.selectDateButton.text = formattedDate
                }, year, month, day)

            datePickerDialog.show()
        }

        binding.fabAddTask.setOnClickListener {
            addTaskEventHandler()
        }
        return binding.root
    }


    private fun addTaskEventHandler() {
        setErrorNull()
        if(!isValidateInputs()) {
            return
        }

        loadProgressBar(true)
        val taskName = binding.etTaskName.text.toString().trim()
        val isComplete = false
        val endDate = binding.selectDateButton.text.toString()

        val task = Task(0, taskName, endDate, isComplete)

        taskViewModel.addTask(task)
        Toast.makeText(requireContext(), "Successfully added new task.", Toast.LENGTH_SHORT)
            .show()
        loadProgressBar(false)
        findNavController().navigate(R.id.action_addTaskFragment_to_taskFragment)
    }

    private fun loadProgressBar(isLoadable: Boolean) {
        if(isLoadable) {
            binding.progressBar.visibility = View.VISIBLE
        }
        else{
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun setErrorNull() {
        binding.etTaskName.error = null
    }

    private fun isValidateInputs(): Boolean {
        if(binding.etTaskName.text.toString().trim().isEmpty()) {
            binding.etTaskName.error = "Field is required"
            return false
        }
        if(binding.selectDateButton.text.toString() == getString(R.string.task_end_date)) {
            Toast.makeText(requireContext(), "Please enter task endDate.", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}