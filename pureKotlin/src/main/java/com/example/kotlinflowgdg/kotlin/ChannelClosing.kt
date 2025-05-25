package com.example.kotlinflowgdg.kotlin

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.NullPointerException


//suspend fun main(): Unit = coroutineScope {
//
//    val channel = Channel<String>(2)
//
//    val names = listOf("Dale", "Harry", "Laura", "Audrey", "BOB")
//
//    launch {
//        names.forEach {  name ->
//            channel.send(name)
//            println("Sending $name")
//            delay(100) // Sending is faster than receiving
//        }
//        channel.close()
//    }
//
//    launch {
//        for (name in channel) {
//            println("Received $name")
//            delay(2000)
//        }
//    }
//}
//
//suspend fun main(){
//
//    flowOf("A", "B", "C") // Flow builder
//        .map { it.lowercase() }
//        .onEach { println(it) }
//        .onStart { println("Before collection started") }
//        .onCompletion { println("After collection finished") }
//        .onEach { processResult("Final operation on $it ") }
//        .catch { emit("Exception Caught!") }
//        .collect()
//}
//
//fun processResult(s: String) {
//}
