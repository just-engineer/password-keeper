package com.github.justengineer.passwordkeeper.blob

import com.github.justengineer.passwordkeeper.blob.dto.BlobDto
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest(controllers = [BlobController::class])
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BlobControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockkBean
    lateinit var service: BlobService

    @Test
    internal fun saveBlob() {
        coEvery { service.saveBlob(any()) } answers { firstArg() }

        val payload = BlobEntity(cypheredPayload = "blobdata")
        client.post()
                .uri("/blob")
                .bodyValue(payload)
                .exchange()
                .expectStatus().isOk
                .expectBody<BlobDto>()
                .consumeWith {
                    assertThat(it.responseBody)
                            .isNotNull
                            .isEqualToComparingFieldByField(payload)
                }


    }
}