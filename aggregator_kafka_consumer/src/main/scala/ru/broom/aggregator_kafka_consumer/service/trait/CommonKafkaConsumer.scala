package ru.broom.aggregator_kafka_consumer.service.`trait`

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import ru.broom.aggregator_kafka_consumer.config.ModuleProperties.Kafka._
import java.util
import java.util.Properties

trait CommonKafkaConsumer {
  protected var properties = new Properties
  properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG)
  properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG)
  properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG)
  properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG)
  properties.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID_CONFIG)
  properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG)
  properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS_CONFIG)
  properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT_CONFIG)
  properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS_CONFIG)

  protected def createKafkaConsumer(topics: util.Collection[String]): KafkaConsumer[String, JsonNode] = {
    val consumer = new KafkaConsumer[String, JsonNode](properties)
    consumer.subscribe(topics)
    consumer
  }
}
