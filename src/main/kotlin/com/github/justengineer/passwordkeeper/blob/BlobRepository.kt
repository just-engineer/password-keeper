package com.github.justengineer.passwordkeeper.blob

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BlobRepository: CoroutineSortingRepository<BlobEntity, String> {
}