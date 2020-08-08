package com.github.justengineer.passwordkeeper.blob

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataMongoTest
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
    fun name() {
        runBlocking {
            val entity = repository.save(BlobEntity("testhexblob"))
            println("entity = $entity")
        }

    }
}