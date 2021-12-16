package com.example.demo

import com.example.kafka.demo1.models.Person
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service


@Service
class KafkaConsumer {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }

    @KafkaListener(topics = ["simple-message-topic"], groupId = "simple-message-group")
    fun consume(@Payload data: ConsumerRecord<String, Person>, @Headers headers: MessageHeaders) {
        val record = data.value() as String;
        val pessoa : Person = Gson().fromJson(record, Person::class.java)
        logger.info("Msg consumed {}", record)
    }
}