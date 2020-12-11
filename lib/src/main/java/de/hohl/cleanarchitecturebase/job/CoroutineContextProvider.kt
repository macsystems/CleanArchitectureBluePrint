package de.hohl.cleanarchitecturebase.job

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineContextProvider {
    fun Main(): CoroutineDispatcher = Dispatchers.Main
    fun IO(): CoroutineDispatcher = Dispatchers.IO
    fun Default(): CoroutineDispatcher = Dispatchers.Default
}
