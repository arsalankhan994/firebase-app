package com.app.mobileapptask.ui.fragment.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobileapptask.databinding.TaskLayoutBinding
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.listener.OnItemClickListener

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskLayoutBinding,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(taskEntity: TaskEntity, position: Int, taskAdapter: TaskAdapter) {
        showData(taskEntity, position, taskAdapter)
    }

    private fun showData(taskEntity: TaskEntity, position: Int, taskAdapter: TaskAdapter) {
        binding.taskName.text = "${taskEntity.taskName} ${taskEntity.id}"
        binding.taskStatus.text = taskEntity.taskStatus

        binding.accept.setOnClickListener {
            val newEntity = taskEntity.copy()
             newEntity.taskStatus = "accept"
            listener.onItemClick(newEntity)
        }

        binding.reject.setOnClickListener {
            taskEntity.apply {
                val newEntity = taskEntity.copy()
                newEntity.taskStatus = "reject"
                listener.onItemClick(newEntity)
            }
        }
    }

    companion object {
        fun create(
            context: Context,
            parent: ViewGroup,
            listener: OnItemClickListener
        ): TaskViewHolder {
            val binding = TaskLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
            return TaskViewHolder(context, binding, listener)
        }
    }
}