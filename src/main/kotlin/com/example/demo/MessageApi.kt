package com.example.demo

import com.example.kafka.demo1.models.Person
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MessageApi (val sendToKafka: SendToKafka) {

    @Value("\${simple-message-topic}")
    private lateinit var topic: String

    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping("/message")
    fun publish(@RequestBody person: Person) {
        logger.info(person.name)
        logger.info(person.lastName)
        //var person = Person(name, lastName)
        sendToKafka.send(topic, person);
    }
}