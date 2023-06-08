package ru.broom.aggregator_kafka_consumer.config

import java.util.Properties

object ModuleProperties {

  private def notNullOrEmpty(s: String): Boolean = {
    s!=null && s.nonEmpty
  }

  private val properties = new Properties()
  properties.load(getClass.getResource("/module.properties").openStream())

  object Kafka {
    val BOOTSTRAP_SERVERS_CONFIG: String = if (notNullOrEmpty(System.getenv("BOOTSTRAP_SERVERS_CONFIG"))) System.getenv("BOOTSTRAP_SERVERS_CONFIG") else properties.getProperty("default.bootstrap.servers")
    val KEY_DESERIALIZER_CLASS_CONFIG: String = if (notNullOrEmpty(System.getenv("KEY_DESERIALIZER_CLASS_CONFIG")))  System.getenv("KEY_DESERIALIZER_CLASS_CONFIG") else properties.getProperty("default.key.deserializer")
    val VALUE_DESERIALIZER_CLASS_CONFIG: String = if (notNullOrEmpty(System.getenv("VALUE_DESERIALIZER_CLASS_CONFIG")))  System.getenv("VALUE_DESERIALIZER_CLASS_CONFIG") else properties.getProperty("default.value.deserializer")
    val GROUP_ID_CONFIG: String = if (notNullOrEmpty(System.getenv("GROUP_ID_CONFIG")))  System.getenv("GROUP_ID_CONFIG") else properties.getProperty("default.group.id")
    val CLIENT_ID_CONFIG: String = if (notNullOrEmpty(System.getenv("CLIENT_ID_CONFIG")))  System.getenv("CLIENT_ID_CONFIG") else properties.getProperty("default.client.id")
    val AUTO_OFFSET_RESET_CONFIG: String = if (notNullOrEmpty(System.getenv("AUTO_OFFSET_RESET_CONFIG")))  System.getenv("AUTO_OFFSET_RESET_CONFIG") else properties.getProperty("default.auto.offset.reset")
    val AUTO_COMMIT_INTERVAL_MS_CONFIG: String = if (notNullOrEmpty(System.getenv("AUTO_COMMIT_INTERVAL_MS_CONFIG")))  System.getenv("AUTO_COMMIT_INTERVAL_MS_CONFIG") else properties.getProperty("default.auto.commit.interval.ms")
    val ENABLE_AUTO_COMMIT_CONFIG: String = if (notNullOrEmpty(System.getenv("ENABLE_AUTO_COMMIT_CONFIG")))  System.getenv("ENABLE_AUTO_COMMIT_CONFIG") else properties.getProperty("default.enable.auto.commit")
    val MAX_POLL_RECORDS_CONFIG: String = if (notNullOrEmpty(System.getenv("MAX_POLL_RECORDS_CONFIG")))  System.getenv("MAX_POLL_RECORDS_CONFIG") else properties.getProperty("default.max.poll.records")

    println("BOOTSTRAP_SERVERS_CONFIG:"+BOOTSTRAP_SERVERS_CONFIG)
    println("KEY_DESERIALIZER_CLASS_CONFIG:"+KEY_DESERIALIZER_CLASS_CONFIG)
    println("VALUE_DESERIALIZER_CLASS_CONFIG:"+VALUE_DESERIALIZER_CLASS_CONFIG)
    println("GROUP_ID_CONFIG:"+GROUP_ID_CONFIG)
    println("CLIENT_ID_CONFIG:"+CLIENT_ID_CONFIG)
    println("AUTO_OFFSET_RESET_CONFIG:"+AUTO_OFFSET_RESET_CONFIG)
    println("AUTO_COMMIT_INTERVAL_MS_CONFIG:"+AUTO_COMMIT_INTERVAL_MS_CONFIG)
    println("ENABLE_AUTO_COMMIT_CONFIG:"+ENABLE_AUTO_COMMIT_CONFIG)
    println("MAX_POLL_RECORDS_CONFIG:"+MAX_POLL_RECORDS_CONFIG)
  }
}
