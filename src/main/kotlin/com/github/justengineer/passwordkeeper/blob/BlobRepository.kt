package com.github.justengineer.passwordkeeper.blob

import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BlobRepository: CoroutineSortingRepository<BlobEntity, String> {

    suspend fun findByUserId(userId: String): List<BlobEntity>
}