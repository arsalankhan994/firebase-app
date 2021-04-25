package com.app.mobileapptask.ui.fragment.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.app.mobileapptask.databinding.TaskLayoutBinding
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.listener.OnItemClickListener

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskLayoutBinding,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(taskEntity: TaskEntity) {
        showData(taskEntity)
    }

    private fun showData(taskEntity: TaskEntity) {
        binding.taskName.text = taskEntity.taskName

        binding.root.setOnClickListener {
            listener.onItemClick(taskEntity)
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