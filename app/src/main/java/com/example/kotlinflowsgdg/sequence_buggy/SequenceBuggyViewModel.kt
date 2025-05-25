package com.example.kotlinflowsgdg.sequence_buggy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowsgdg.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SequenceBuggyViewModel : ViewModel() {

    fun getSelection() = TaskRepository.tasks

    fun onTaskClicked(taskIndex: Int, activate: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTask(taskIndex, activate)
        }
    }

    private suspend fun updateTask(taskIndex: Int, activate: Boolean) {
        TaskRepository.updateTask(index = taskIndex, activate = activate)
    }
}