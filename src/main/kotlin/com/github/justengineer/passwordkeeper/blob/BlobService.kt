package com.github.justengineer.passwordkeeper.blob

import one.util.streamex.StreamEx
import org.springframework.stereotype.Service

@Service
class BlobService(private val repository: BlobRepository) {

    suspend fun saveBlob(entity: BlobEntity): BlobEntity {
        return repository.save(entity)
    }

    suspend fun getById(id: String): BlobEntity? {
        return repository.findById(id)
    }

    suspend fun getLatestRecords(userId: String): List<BlobEntity> {
        val recordsByUserId = repository.findByUserId(userId)

        StreamEx.of(recordsByUserId)

                .groupingBy { it.recordId }



    }
}