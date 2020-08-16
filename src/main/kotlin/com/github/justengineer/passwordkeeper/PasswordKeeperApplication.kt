package com.github.justengineer.passwordkeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
class PasswordKeeperApplication

fun main(args: Array<String>) {
    runApplication<PasswordKeeperApplication>(*args)
}
