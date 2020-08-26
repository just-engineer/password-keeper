package com.github.justengineer.passwordkeeper.blob

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.aggregation.TypedAggregation
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findDistinct
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.query.where

class BlobCustomRepositoryImpl(val template: ReactiveMongoTemplate) : BlobCustomRepository {

    override suspend fun findLatestRecordsByUserId(userId: String): Flow<BlobEntity> {

        val userIdQuery = where(BlobEntity::userId).isEqualTo(userId)

        val sort = sort(Sort.Direction.DESC, BlobEntity::recordVersion.name)
        val group = group(BlobEntity::recordId.name)
                .first(ROOT)
                .`as`("doc")

        val replaceRoot = replaceRoot("doc")
        val pipeline = listOf(match(userIdQuery), sort, group, replaceRoot)
        val aggregation = TypedAggregation(BlobEntity::class.java, pipeline)
        return template.aggregate(aggregation, BlobEntity::class.java)
                .log()
                .asFlow()
    }


}