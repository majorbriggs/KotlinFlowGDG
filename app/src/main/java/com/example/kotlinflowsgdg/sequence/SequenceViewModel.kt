package com.example.kotlinflowsgdg.sequence

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowsgdg.TaskRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SequenceViewModel : ViewModel() {

    private val updateJobsFlow = MutableSharedFlow<Job>(extraBufferCapacity = 10)
    fun getSelection() = TaskRepository.tasks

    init {
        updateJobsFlow.onEach { job ->
            job.join()
        }.launchIn(viewModelScope)
    }
    fun <T> Flow<T>.observe(
        lifecycleOwner: LifecycleOwner,
        collector: suspend (T) -> Unit,
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collect(collector)
                }
        }
    }

    fun onTaskClicked(taskIndex: Int, activate: Boolean) {
        val lazyJob = viewModelScope.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
            updateTask(taskIndex, activate)
        }
        updateJobsFlow.tryEmit(lazyJob)
    }

    private suspend fun updateTask(taskIndex: Int, activate: Boolean) {
        TaskRepository.updateTask(index = taskIndex, activate = activate)
    }
}