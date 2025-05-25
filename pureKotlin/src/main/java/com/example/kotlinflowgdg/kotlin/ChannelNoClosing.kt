package com.example.kotlinflowgdg.kotlin

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//suspend fun main(): Unit = coroutineScope {
//
//    val channel = Channel<String>(2, onBufferOverflow = BufferOverflow.DROP_LATEST)
//
//    val names = listOf("Dale", "Harry", "Laura", "Audrey", "BOB")
//
//    launch {
//        names.forEach {  name ->
//            channel.send(name)
//            println("Sending $name")
//            delay(100) // Sending is faster than receiving
//        }
//    }
//
//    launch {
//        while(true) {
//            val received = channel.receive()
//            println("Received $received")
//            delay(2000)
//        }
//    }
//}