package com.example.kotlinflowsgdg

enum class TaskState{
    DISABLED, IN_PROGRESS, ENABLED
}

data class TaskSummaryState(
    val task1: TaskState,
    val task2: TaskState,
    val task3: TaskState,
)