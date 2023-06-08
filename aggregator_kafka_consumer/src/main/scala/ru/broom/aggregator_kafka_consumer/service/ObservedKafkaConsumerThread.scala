package ru.broom.aggregator_kafka_consumer.service

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.apache.kafka.clients.consumer.KafkaConsumer
import ru.broom.aggregator_kafka_consumer.service.`trait`.CommonKafkaConsumer
import ru.broom.aggregator_kafka_consumer.service.exception.KafkaObserverBuildException
import rx.schedulers.Schedulers
import rx.subjects.ReplaySubject

import java.time.Duration
import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters.setAsJavaSetConverter
import scala.reflect.ClassTag

class ObservedKafkaConsumerThread[O:ClassTag](topic: String) extends Thread with CommonKafkaConsumer {
  private val objectMapper = new ObjectMapper
  private val clazzTag = scala.reflect.classTag[O].runtimeClass
  private val kafkaConsumer: KafkaConsumer[String, JsonNode] = createKafkaConsumer(Set(topic).asJava)
  private val duration: Duration = Duration.ofSeconds(100)
  val observableObject: ReplaySubject[O] = ReplaySubject.createWithTime(3, TimeUnit.MINUTES, Schedulers.immediate())

  override def run(): Unit = {
    while(true){
      val consumerRecords = kafkaConsumer.poll(duration)
      if (consumerRecords!= null && !consumerRecords.isEmpty){
        if (consumerRecords.count()>1)
          throw KafkaObserverBuildException(s"Expected single record consumer because zip function usages but got - ${consumerRecords.count()}")
        val obj = objectMapper.readValue(consumerRecords.iterator().next().value().toString, clazzTag).asInstanceOf[O]
        observableObject.onNext(obj)
      }
    }
  }
  this.start()
}


