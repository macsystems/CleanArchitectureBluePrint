package de.hohl.cleanarchitecturebase

interface Source<Entity> {
    fun fetchData(): Entity
}