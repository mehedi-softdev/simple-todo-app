package com.mehedisoftdev.simpletodoapp.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehedisoftdev.simpletodoapp.R
import com.mehedisoftdev.simpletodoapp.models.Task
import com.mehedisoftdev.simpletodoapp.utils.Constants
import com.mehedisoftdev.simpletodoapp.viewmodels.TaskViewModel
import java.util.Date


class TaskAdapter(private val taskViewModel: TaskViewModel) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffUtilCallBack()){


    // view holder class
    inner class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val taskName = view.findViewById<TextView>(R.id.row_task_name)
        val isComplete = view.findViewById<CheckBox>(R.id.row_is_complete_check_box)

        init {
            isComplete.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = getItem(position)
                    val updatedTask = Task(
                        task.taskId,
                        task.taskName,
                        task.endDate,
                        isChecked
                    )
                    taskViewModel.updateTask(updatedTask)
                }
            }
        }

        fun bind(task: Task) {
            taskName.text = task.taskName
            isComplete.isChecked = task.isComplete

            if (task.isComplete) {
                taskName.paintFlags = taskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.overlay_color
                    )
                )
            } else {
                taskName.paintFlags = taskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.task_row_background
                    )
                )
            }
        }

    }

    // diff util impl
    class DiffUtilCallBack: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_row, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task : Task = getItem(position)

        val endDate = Constants.stringDateToDate(task.endDate)
        val currentDate = Date()

        if (endDate != null && currentDate.after(endDate)) {
            // Task is past the end date, mark it as complete
            task.isComplete = true
            // Update the task in your ViewModel or database
            taskViewModel.updateTask(task)
        }

        holder.bind(task) // pass task to view holder bind fun()
    }
}