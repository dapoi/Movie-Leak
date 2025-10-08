package com.dapascript.movieleak.utils

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun <T> StateFlow<T>.getOrAwaitValue(): T = runBlocking {
    return@runBlocking first()
}