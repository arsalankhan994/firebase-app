package com.app.mobileapptask.ui.fragment.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mobileapptask.databinding.TaskListFragmentBinding
import com.app.mobileapptask.entity.TaskEntity
import com.app.mobileapptask.listener.OnItemClickListener
import com.app.mobileapptask.repository.local.TaskDao
import com.app.mobileapptask.repository.local.TaskRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private var _binding: TaskListFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var taskRepository: TaskRepository
    private val viewModel: TaskListViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TaskListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.layoutManager = linearLayManager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        ))

        // delete task on click
        adapter = TaskAdapter(requireContext(), object : OnItemClickListener {
            override fun onItemClick(item: TaskEntity) {
                viewModel.deleteTask(taskEntity = item)
            }
        })

        // add task on fab click
        binding.fabIcon.setOnClickListener {
            viewModel.addTask(TaskEntity(taskName = "Task   ${Random.nextInt(1, 1000)}"))
        }

        binding.recyclerView.adapter = adapter

        taskRepository.getTasks().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                (binding.recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(positionStart, 0)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

