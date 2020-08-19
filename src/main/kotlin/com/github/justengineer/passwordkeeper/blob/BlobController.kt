package com.github.justengineer.passwordkeeper.blob

import com.github.justengineer.passwordkeeper.blob.dto.BlobDto
import com.github.justengineer.passwordkeeper.blob.dto.CreateBlobRequest
import com.github.justengineer.passwordkeeper.blob.dto.toDto
import com.github.justengineer.passwordkeeper.blob.dto.toEntity
import org.springframework.web.bind.annotation.*

@RestController
class BlobController(val service: BlobService) {

    @PostMapping("/blob")
    suspend fun saveBlob(@RequestBody request: CreateBlobRequest): BlobDto {
        val entity = service.saveBlob(request.toEntity())
        return entity.toDto()
    }

    @GetMapping("/blob/{id}")
    suspend fun getById(@PathVariable id: String): BlobDto? {
        val entity = service.getById(id)
        return entity?.toDto()
    }

    @GetMapping("/blob/all-records-latest")
    suspend fun getLatestRecords(): List<BlobDto> {
        return service.getLatestRecords("")
                .map { it.toDto() }
    }


}