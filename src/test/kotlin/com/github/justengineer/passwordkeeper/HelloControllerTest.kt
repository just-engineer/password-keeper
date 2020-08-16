package com.github.justengineer.passwordkeeper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest(controllers = [HelloController::class])
internal class HelloControllerTest(@Autowired val client: WebTestClient){
    @Test
    internal fun name() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectBody<String>()
                .isEqualTo("Hello")
    }
}