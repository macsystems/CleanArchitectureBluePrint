package de.hohl.cleanarchitecturebase.job

import kotlinx.coroutines.Deferred


interface UseCase<out R, T> {
    fun execute(params: T?): Deferred<R>
}
