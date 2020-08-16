package com.github.justengineer.passwordkeeper.blob

import com.github.justengineer.passwordkeeper.MongoAuditConfig
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer

@DataMongoTest
@Import(MongoAuditConfig::class)
class BlobRepositoryTest {

    @Autowired
    lateinit var repository: BlobRepository

    companion object {
        @JvmStatic
        val mongo: MongoDBContainer = MongoDBContainer("mongo:4.2")

        init {
            mongo.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun init(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongo.replicaSetUrl }
        }
    }

    @Test
    fun auditingSave() {
        runBlocking {
            val payload = "testhexblob"
            val entity = repository.save(BlobEntity(cypheredBlob = payload))
            assertThat(entity.id).isNotNull()
            assertThat(entity.createdDate).isNotNull()
            assertThat(entity.cypheredBlob).isEqualTo(payload)
        }
    }
}