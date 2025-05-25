package com.example.kotlinflowgdg.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

//suspend fun main(): Unit = coroutineScope {
//
//    val flowWithBuffer = flow {
//        (1..10).forEach {
//            emit(it)
//            delay(100)
//        }
//    }.buffer(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
//
//    launch {
//        flowWithBuffer.collect {
//            println(it)
//            delay(200)
//        }
//    }
//}
