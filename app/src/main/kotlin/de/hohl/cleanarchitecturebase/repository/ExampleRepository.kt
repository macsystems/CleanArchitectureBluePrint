package de.hohl.cleanarchitecturebase.repository

import de.hohl.cleanarchitecturebase.Source
import de.hohl.cleanarchitecturebase.cache.ExampleLocalEntity
import de.hohl.cleanarchitecturebase.remote.ExampleNetworkEntity
import de.hohl.cleanarchitecturebase.remote.ExampleRemoteEntity

class ExampleRepository(
    private val localSource: Source<ExampleLocalEntity>,
    private val remoteSource: Source<ExampleNetworkEntity>
) : Repository<Source<ExampleLocalEntity>, Source<ExampleRemoteEntity>> {


}