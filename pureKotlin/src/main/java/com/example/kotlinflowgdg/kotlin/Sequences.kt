package com.example.kotlinflowgdg.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun <T> computeIoValue(): T
{
    return T
}

fun <T> computeCpuValue(): T
{
    return T
}
suspend fun main(): Unit =
    runBlocking {

        val f1 =  channelFlow {
            // send from one coroutine
            launch(Dispatchers.IO) {
                send(computeIoValue())
            }
            // send from another coroutine, concurrently
            launch(Dispatchers.Default) {
                send(computeCpuValue())
            }
        }

        fun observeData() = flow {
            myAwesomeInterface.addListener{ result ->
                emit(result) // NOT ALLOWED
            }
        }

        fun observeData() = callbackFlow {
            myAwesomeInterface.addListener{ result ->
                trySend(result) // ALLOWED
            }
            awaitClose{ myAwesomeInterface.removeListener() }
        }


        val channel = Channel<Int>()

        launch {
            for (x in 1..5) {
                // here might be heavy CPU-consuming computation
                channel.send(x * x)
            }
        }

        launch {
            repeat(5) {
                println(channel.receive())
            }
        }

        val flow1 = flowOf("A", "B", "C")
        val flow2 = flowOf("D")
            .onEach { delay(1000) }

        val sharedFlow = merge(flow1, flow2).shareIn(
            scope = this,
            started = SharingStarted.Lazily,
        )
        delay(100)
        launch {
            sharedFlow.collect { println("#1 $it") }
        }
        delay(1000)
        launch {
            sharedFlow.collect { println("#2 $it") }
        }
    }

//
//val withoutSequence = measureTimeMillis {
//    (1..1_000_000)
//        .filter {
//            it % 2 == 1
//        } // each step results in a whole new collection
//        .map {
//            (it * 2).toString()
//        } // 48ms
//}
//
//val sequenceCollected = measureTimeMillis {
//    (1..1_000_000).asSequence()
//        .filter {
//            it % 2 == 1 // each element is filtered and mapped one by one
//        }.map {
//            (it * 2).toString()
//        }.toList()
//}
//
//val sequenceNotCollected = measureTimeMillis {
//    (1..1_000_000).asSequence()
//        .filter {
//            it % 2 == 1 // each element is filtered and mapped one by one
//        }.map {
//            (it * 2).toString()
//        }
//} // 6ms
//
//
//
//println("withoutSequence: $withoutSequence") // 48ms
//println("notCollected: $sequenceNotCollected") // 6ms
//println("sequenceCollected: $sequenceCollected") // 32ms
//}
//
//fun calculate(it: Int): String {
//    return ""
//}
//
//fun f() = flow {
//    emit("START")
//    (1..10).forEach {
//        emit(calculate(it))
//    }
//    emit("STOP")
//}.flowOn(Dispatchers.Default)
//
//fun updateUi(s: String) {
//}
//
//fun main() = runBlocking {
//    val scope = CoroutineScope(Dispatchers.Default)
//
//    scope.launch {
//        f().collect {
//            updateUi(it)
//        }
//    }
//
//    f().onEach {
//        updateUi(it)
//    }.launchIn(scope)
//}





