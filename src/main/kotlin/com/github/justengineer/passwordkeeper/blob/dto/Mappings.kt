package com.github.justengineer.passwordkeeper.blob.dto

import com.github.justengineer.passwordkeeper.blob.BlobEntity
import java.util.*

fun CreateBlobRequest.toEntity(): BlobEntity {
    return BlobEntity(id = null,
            recordId = recordId,
            cypheredPayload = cypheredPayload,
            recordVersion = recordVersion,
            userId = UUID.randomUUID().toString(),
            createdDate = null
    )
}

fun BlobEntity.toDto(): BlobDto {
    return BlobDto(id, createdDate, recordId, cypheredPayload, recordVersion)
}