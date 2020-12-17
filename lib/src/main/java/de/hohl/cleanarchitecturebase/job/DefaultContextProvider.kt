package de.hohl.cleanarchitecturebase.job

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


/**
 * Default impl. of CoroutineContextProvider which returns expected
 * Dispatchers to launch Coroutines
 */
object DefaultContextProvider : CoroutineContextProvider {

    override fun Default(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun IO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun Main(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}