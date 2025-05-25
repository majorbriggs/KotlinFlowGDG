package com.example.kotlinflowgdg.kotlin

import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main(): Unit = runBlocking {
//
//    val names = listOf("Dale", "Harry", "Laura", "Audrey", "BOB")
//
//    val channel = produce {
//        names.forEach {
//            send(it)
//        }
//    }
//
//    launch {
//        for (name in channel) {
//            println("Consumer 1 received $name")
//            delay(300)
//        }
//    }
//
//    launch {
//        for (name in channel) {
//            println("Consumer 2 received $name")
//            delay(300)
//        }
//    }
//}
//Consumer 1 received Dale
//Consumer 2 received Harry
//Consumer 1 received Laura
//Consumer 2 received Audrey
//Consumer 1 received BOB