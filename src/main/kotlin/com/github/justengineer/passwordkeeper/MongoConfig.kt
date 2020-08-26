package com.github.justengineer.passwordkeeper

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@Configuration(proxyBeanMethods = false)
class MongoConfig