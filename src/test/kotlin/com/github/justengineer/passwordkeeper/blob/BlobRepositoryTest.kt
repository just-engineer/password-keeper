package com.github.justengineer.passwordkeeper.blob

import com.github.justengineer.passwordkeeper.MongoConfig
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import one.util.streamex.LongStreamEx
import one.util.streamex.StreamEx
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import org.springframework.dao.DuplicateKeyException
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@DataMongoTest
@Import(MongoConfig::class)
@Testcontainers
class BlobRepositoryTest(@Autowired val repository: BlobRepository) {

    companion object {
        @JvmStatic
        @Container
        val mongo: MongoDBContainer = MongoDBContainer("mongo:4.2")

        @JvmStatic
        @DynamicPropertySource
        fun init(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongo.replicaSetUrl }
        }
    }

    @Test
    fun auditingSave() = runBlocking<Unit> {
        val payload = "testhexblob"
        val entity = repository.save(BlobEntity(payload))
        assertThat(entity.id).isNotNull()
        assertThat(entity.createdDate).isNotNull()
        assertThat(entity.cypheredPayload).isEqualTo(payload)
        assertThat(entity.recordId).isNotNull()
        assertThat(entity.recordVersion).isEqualTo(0L)
    }

    @Test
    fun checkUniqueRecordIdVersion() = runBlocking<Unit> {
        val payload = "testhexblob"
        val entity = BlobEntity(payload)
        val saved = repository.save(entity)

        val duplicate = BlobEntity(
                cypheredPayload = payload,
                recordId = saved.recordId,
                recordVersion = saved.recordVersion,
                userId = UUID.randomUUID().toString())

        assertThatThrownBy { runBlocking { repository.save(duplicate) } }
                .isInstanceOf(DuplicateKeyException::class.java)
    }

    @Test
    fun findLatestRecords() {
        val userId = UUID.randomUUID().toString()
        val size = 5
        val blobs = StreamEx.generate { UUID.randomUUID().toString() }
                .limit(size.toLong())
                .flatMap {
                    streamRecordVersions(it, userId)
                }.toList()
        val records = runBlocking {
            val savedBlobs = repository.saveAll(blobs).toList()
            println("savedBlobs = $savedBlobs")
            repository.findLatestRecordsByUserId(userId).toList()
        }
        assertThat(records).hasSize(size)
        println("records = $records")
    }

    private fun streamRecordVersions(recordId: String, userId: String): StreamEx<BlobEntity>? {
        return LongStreamEx.range(5)
                .mapToObj {
                    BlobEntity(
                            cypheredPayload = "cypheredPayload",
                            recordId = recordId,
                            recordVersion = it,
                            userId = userId)
                }
    }
}