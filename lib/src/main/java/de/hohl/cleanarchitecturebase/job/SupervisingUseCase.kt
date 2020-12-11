package de.hohl.cleanarchitecturebase.job

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Base UseCase which is Supervising. when canceled all parent jobs get canceled.
 * When parent Jobs fail, others will still executed
 */
abstract class SupervisingUseCase<T, Params>(private val dispatcher: CoroutineContextProvider) :
    CoroutineScope {

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatcher.IO() + job

    abstract suspend fun buildSuspendUseCase(params: Params? = null): T

    open fun execute(params: Params? = null): Deferred<T> {
        return async {
            buildSuspendUseCase(params)
        }
    }

    fun cancel() {
        job.cancel()
    }

    fun restartJob() {
        job = SupervisorJob()
    }
}
