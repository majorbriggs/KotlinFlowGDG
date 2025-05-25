package com.example.kotlinflowsgdg

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object TaskRepository {

    private val _tasks = MutableStateFlow(
        TaskSummaryState(
            task1 = TaskState.DISABLED,
            task2 = TaskState.DISABLED,
            task3 = TaskState.DISABLED
        )
    )
    val tasks: Flow<TaskSummaryState> = _tasks

    suspend fun updateTask(index: Int, activate: Boolean) { // simulate long runnning network call
        val oldTasks = _tasks.value
        val newTargetState = if (activate) TaskState.ENABLED else TaskState.DISABLED
        val tasksInProgress = when (index) {
            0 -> oldTasks.copy(task1 = TaskState.IN_PROGRESS)
            1 -> oldTasks.copy(task2 = TaskState.IN_PROGRESS)
            else -> oldTasks.copy(task3 = TaskState.IN_PROGRESS)
        }
        _tasks.value = tasksInProgress
        delay(1000)
        val doneTasks = when (index) {
            0 -> oldTasks.copy(task1 = newTargetState)
            1 -> oldTasks.copy(task2 = newTargetState)
            else -> oldTasks.copy(task3 = newTargetState)
        }
        _tasks.value = doneTasks
    }
}