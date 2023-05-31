package com.mehedisoftdev.simpletodoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mehedisoftdev.simpletodoapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private var _binding : FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_addFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // clean up memory
    }
}