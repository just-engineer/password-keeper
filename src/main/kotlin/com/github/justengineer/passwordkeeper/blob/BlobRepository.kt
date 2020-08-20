package com.github.justengineer.passwordkeeper.blob

import org.springframework.data.mongodb.repository.Meta
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BlobRepository: CoroutineSortingRepository<BlobEntity, String>, BlobCustomRepository {


}