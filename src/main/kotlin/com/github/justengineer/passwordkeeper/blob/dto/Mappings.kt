package com.github.justengineer.passwordkeeper.blob.dto

import com.github.justengineer.passwordkeeper.blob.BlobEntity
import java.util.*

fun CreateBlobRequest.toEntity(): BlobEntity {
    return BlobEntity(
            recordId = recordId,
            cypheredPayload = cypheredPayload,
            recordVersion = recordVersion,
            userId = UUID.randomUUID().toString()
    )
}

fun BlobEntity.toDto(): BlobDto {
    return BlobDto(id, createdDate, recordId, cypheredPayload, recordVersion)
}