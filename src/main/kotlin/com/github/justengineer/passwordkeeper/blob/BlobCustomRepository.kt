package com.github.justengineer.passwordkeeper.blob

import kotlinx.coroutines.flow.Flow

interface BlobCustomRepository {

    suspend fun findLatestRecordsByUserId(userId: String): Flow<BlobEntity>

}