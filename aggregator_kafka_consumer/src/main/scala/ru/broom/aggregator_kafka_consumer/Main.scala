package ru.broom.aggregator_kafka_consumer

import ru.broom.aggregator_kafka_consumer.service.AggregateKafkaService
import ru.broom.common_trade.model.AggregatedTradeVolume
import ru.broom.hibernate_postgres_common.service.AggregatedTradeVolumeDao
import rx.Subscriber
import rx.schedulers.Schedulers



object Main extends App {

  val aggregateKafkaService = new AggregateKafkaService()
  val aggregatedTradeVolumeDao = new AggregatedTradeVolumeDao()
  aggregateKafkaService.currenciesTopicsAggregatedObserverMap.foreach(e=>{
    e._2.subscribeOn(Schedulers.newThread()).subscribe(
      new Subscriber[AggregatedTradeVolume]() {
        override def onCompleted(): Unit = ???

        override def onError(e: Throwable): Unit = {
          e.printStackTrace()
        }

        override def onNext(t: AggregatedTradeVolume): Unit = {
          aggregatedTradeVolumeDao.persist(t)
        }
      })
  })



  while(true){
    Thread.sleep(1000)
  }

}