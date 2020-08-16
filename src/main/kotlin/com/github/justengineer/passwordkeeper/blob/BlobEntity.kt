package com.github.justengineer.passwordkeeper.blob

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Document
data class BlobEntity(@Id var id: String? = null,
                      @CreatedDate var createdDate: Instant? = null,
                      val cypheredBlob: String) {
}