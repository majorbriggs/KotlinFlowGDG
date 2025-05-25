package com.example.kotlinflowsgdg.sequence_channel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowsgdg.TaskRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class SequenceChannelViewModel : ViewModel() {

    private val updateJobsChannel = Channel<Job>(capacity = Channel.UNLIMITED)
        .apply {
            viewModelScope.launch {
                consumeEach { it.join() }
            }
        }

    fun getSelection() = TaskRepository.tasks

    fun onTaskClicked(taskIndex: Int, activate: Boolean) {
        val lazyJob = viewModelScope.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
            updateTask(taskIndex, activate)
        }
        updateJobsChannel.trySend(lazyJob)
    }



    private suspend fun updateTask(taskIndex: Int, activate: Boolean) {
        TaskRepository.updateTask(index = taskIndex, activate = activate)
    }
}