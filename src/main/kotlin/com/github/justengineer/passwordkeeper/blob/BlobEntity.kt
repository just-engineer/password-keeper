package com.github.justengineer.passwordkeeper.blob

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

@Document
@CompoundIndex(def = "{ 'recordId':1, 'recordVersion':1 }", unique = true)
data class BlobEntity(@Id val id: String?,
                      @CreatedDate val createdDate: Instant?,
                      @Indexed val recordId: String,
                      val cypheredPayload: String,
                      @Indexed val userId: String,
                      val recordVersion: Long) {
    constructor(userId: String, cypheredPayload: String) :
            this(null, null, UUID.randomUUID().toString(), cypheredPayload, userId, 0L)

    constructor(cypheredPayload: String) :
            this(null, null, UUID.randomUUID().toString(), cypheredPayload, UUID.randomUUID().toString(), 0L)

}