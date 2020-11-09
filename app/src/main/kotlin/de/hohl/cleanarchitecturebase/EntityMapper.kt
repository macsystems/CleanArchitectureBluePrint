package de.hohl.cleanarchitecturebase

interface EntityMapper<SourceEntity, DestinationEntity> {
    /**
     * Map Entity from eg. Network Layer to Domian
     */
    fun mapToSourceEntity(entity: DestinationEntity): SourceEntity

    /**
     * Map Domain Entity to eg. Network Entity
     */
    fun mapToDestinationEntity(entity: SourceEntity): DestinationEntity

    /**
     * Batch Map from Source to Destination
     */
    fun mapAllSourceToDestination(entity: List<SourceEntity>): List<DestinationEntity> {
        return entity.map { mapToDestinationEntity(it) }
    }

    /**
     * Batch Map from Destination to Source
     */
    fun mapAllDestinationToSource(entity: List<DestinationEntity>): List<SourceEntity> {
        return entity.map { mapToSourceEntity(it) }
    }

}