package com.github.justengineer.passwordkeeper

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    suspend fun hello(): String {
        return "Hello"
    }
}