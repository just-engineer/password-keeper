package com.github.justengineer.passwordkeeper.blob

import org.springframework.web.bind.annotation.*

@RestController
class BlobController(val service: BlobService) {

    @PostMapping("/blob")
    suspend fun saveBlob(@RequestBody blob: BlobEntity): BlobEntity {
        return service.saveBlob(blob)
    }




}