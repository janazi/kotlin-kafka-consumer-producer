package com.example.demo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class SendToKafka (val kafkaConfig: KafkaConfig) {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val logger = LoggerFactory.getLogger(javaClass)
    private val producerJson = kafkaConfig.producerJson()

    fun send(topic: String, obj: Any) {
        val node = ObjectMapper().valueToTree<JsonNode>(obj)
        val record : ProducerRecord<String, JsonNode> = ProducerRecord(topic, node)
        producerJson.send(record) { metadata: RecordMetadata?, exception: Exception? ->
            println(exception?: metadata)
        }
    }
}