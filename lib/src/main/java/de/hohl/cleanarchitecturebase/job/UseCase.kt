package de.hohl.cleanarchitecturebase.job


interface UseCase<out R, T> {
    fun execute(params: T?): R
}
