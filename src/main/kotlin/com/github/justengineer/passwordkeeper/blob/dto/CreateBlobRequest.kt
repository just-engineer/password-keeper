package com.github.justengineer.passwordkeeper.blob.dto

import com.github.justengineer.passwordkeeper.blob.BlobEntity

data class CreateBlobRequest(val recordId: String,
                             val cypheredPayload: String,
                             val recordVersion: Long = 0L)


