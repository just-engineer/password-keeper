package com.github.justengineer.passwordkeeper

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@Configuration(proxyBeanMethods = false)
class MongoAuditConfig {
}