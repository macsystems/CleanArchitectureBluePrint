package de.hohl.cleanarchitecturebase.job

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Base UseCase which is Supervising. when canceled all parent jobs get canceled.
 * When parent Jobs fail, others will still executed
 */
abstract class SupervisingUseCase<R, Params>(private val dispatcher: CoroutineContextProvider) :
    CoroutineScope, UseCase<R, Params> {

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatcher.IO() + job

    abstract suspend fun buildSuspendUseCase(params: Params? = null): R

    override fun execute(params: Params?): Deferred<R> {
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
