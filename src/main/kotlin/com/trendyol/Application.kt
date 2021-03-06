package com.trendyol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.support.atomic.RedisAtomicLong


@SpringBootApplication
@EnableCaching
class Application


fun main(args: Array<String>) {
    runApplication<Application>(*args)
}


@Configuration
@EnableRedisRepositories(value = ["com.trendyol.repository.redis"])
@EnableJpaRepositories(value = ["com.trendyol.repository.mysql"])
class Config {

    @Bean
    fun redisTemplate(connectionFactory:RedisConnectionFactory): RedisTemplate<*, *> {
        val template = RedisTemplate<Any, Any>()
        template.setConnectionFactory(connectionFactory)
        template.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        return template
    }

    @Bean
    fun shortLinkCounter(connectionFactory:RedisConnectionFactory): RedisAtomicLong {
        return RedisAtomicLong("SHORTLINK_COUNTER", connectionFactory)
    }
}
