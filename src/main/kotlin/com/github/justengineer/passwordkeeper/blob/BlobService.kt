package com.github.justengineer.passwordkeeper.blob

import org.springframework.stereotype.Service

@Service
class BlobService(private val repository: BlobRepository) {

    suspend fun saveBlob(entity: BlobEntity): BlobEntity {
        return repository.save(entity)
    }
}