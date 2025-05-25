//package com.example.kotlinflowgdg.kotlin
//
//import kotlinx.coroutines.channels.BufferOverflow
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.emitAll
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.launch
//
//suspend fun main() = coroutineScope{
//
//    val mutableSharedFlow = MutableSharedFlow<Int>(
//        replay = 1,
//        extraBufferCapacity = 2,
//        onBufferOverflow = BufferOverflow.DROP_OLDEST)
//
//    launch {
//        mutableSharedFlow.collect {
//            delay(1000)
//            println("Collected: $it")
//        }
//    }
//    // replay value didn't have any effect
//    mutableSharedFlow.emitAll(flowOf(1, 2, 3, 4, 5))
//    // Collected: 3, 4, 5
//}