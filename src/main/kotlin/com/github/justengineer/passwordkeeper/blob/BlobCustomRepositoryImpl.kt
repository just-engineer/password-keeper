package com.github.justengineer.passwordkeeper.blob

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.aggregation.AggregationExpression
import org.springframework.data.mongodb.core.aggregation.TypedAggregation
import org.springframework.data.mongodb.core.distinct
import org.springframework.data.mongodb.core.query.*
import org.springframework.data.mongodb.core.query.Query.query

class BlobCustomRepositoryImpl(val template: ReactiveMongoTemplate) : BlobCustomRepository {

    override suspend fun findLatestRecordsByUserId(userId: String): Flow<BlobEntity> {
        val criteria = Criteria("userId").isEqualTo(userId)


        val userIdQuery = where(BlobEntity::userId).isEqualTo(userId)

//        val maxProject = project(BlobEntity::class.java)
//                .and(AccumulatorOperators.Max.maxOf("recordVersion"))
//                .filter("recordVersion", AccumulatorOperators.Max.maxOf("recordVersion"))
//                .`as`("recordVersion")


        val group = group("1")
                .max(BlobEntity::recordVersion.name)
                .`as`("latest")


        val aggregation = TypedAggregation(BlobEntity::class.java, match(userIdQuery), group)

        return template.aggregate(aggregation, BlobEntity::class.java)
//                .log()
                .asFlow()

//        val query = userIdQuery
//
//        val find = template.find(Query(userIdQuery), BlobEntity::class.java)
//
//        template.query(BlobEntity::class.java)
//                .distinct(BlobEntity::recordId)
//                .matching(query)
//
//
//        return template.findDistinct(Query(criteria), "recordId", BlobEntity::class.java, BlobEntity::class.java)
//                .log()
//                .asFlow()
    }


}