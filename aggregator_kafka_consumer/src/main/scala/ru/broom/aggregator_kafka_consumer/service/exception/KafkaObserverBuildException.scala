package ru.broom.aggregator_kafka_consumer.service.exception

case class KafkaObserverBuildException(private val message: String = "",
                                       private val cause: Throwable = None.orNull) extends Exception(message, cause)