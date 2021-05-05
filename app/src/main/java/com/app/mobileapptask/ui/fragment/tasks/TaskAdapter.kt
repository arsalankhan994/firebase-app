package com.app.mobileapptask.ui.fragment.tasks

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.listener.OnItemClickListener

class TaskAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : ListAdapter<TaskEntity, TaskViewHolder>(MOVIE_COMPARATOR) {

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<TaskEntity>() {
            override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(context, parent, listener)
    }

    override fun submitList(list: List<TaskEntity>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position, this)
        }
    }
}