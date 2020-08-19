package com.github.justengineer.passwordkeeper.blob.dto

import java.time.Instant

data class BlobDto(val id: String?,
                   val createdDate: Instant?,
                   val recordId: String,
                   val cypheredPayload: String,
                   val recordVersion: Long)