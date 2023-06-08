package ru.broom.binance_kafka_producer.service

import ru.broom.binance_kafka_producer.service.`trait`.CommonKafkaProducer
import ru.broom.binance_kafka_producer.service.`trait`.BinanceProducerObserver
import ru.broom.common_trade.model.{Price, StackOrders}
import rx.Subscriber

class BinanceKafkaStream extends CommonKafkaProducer with BinanceProducerObserver {
  Thread.sleep(15000)
  def startStream(): Unit = {
    for (topic <- currenciesPriceObserverMap.keySet) {
      createTopic(topic)
      currenciesPriceObserverMap(topic).subscribe(new Subscriber[Price]() {
        override def onCompleted(): Unit = ???
        override def onError(e: Throwable): Unit = ???
        override def onNext(price: Price): Unit = {
          sendEntity(topic, price)
        }
      })
    }
    for (topic <- bidCallOrdersObserverMap.keySet) {
      createTopic(topic)
      bidCallOrdersObserverMap(topic).subscribe(new Subscriber[StackOrders]() {
        override def onCompleted(): Unit = ???
        override def onError(e: Throwable): Unit = ???
        override def onNext(stackOrders: StackOrders): Unit = {
            sendEntity(topic, stackOrders)
        }
      })
    }

    for (topic <- askCallOrdersObserverMap.keySet) {
      createTopic(topic)
      askCallOrdersObserverMap(topic).subscribe(new Subscriber[StackOrders]() {
        override def onCompleted(): Unit = ???
        override def onError(e: Throwable): Unit = ???
        override def onNext(stackOrders: StackOrders): Unit = {
          sendEntity(topic, stackOrders)
        }
      })
    }

    for (topic <- sellPerfectOrdersObserverMap.keySet) {
      createTopic(topic)
      sellPerfectOrdersObserverMap(topic).subscribe(new Subscriber[StackOrders]() {
        override def onCompleted(): Unit = ???
        override def onError(e: Throwable): Unit = ???
        override def onNext(stackOrders: StackOrders): Unit = {
            sendEntity(topic, stackOrders)
        }
      })
    }

    for (topic <- buyPerfectOrdersObserverMap.keySet) {
      createTopic(topic)
      buyPerfectOrdersObserverMap(topic).subscribe(new Subscriber[StackOrders]() {
        override def onCompleted(): Unit = ???
        override def onError(e: Throwable): Unit = ???
        override def onNext(stackOrders: StackOrders): Unit = {
          sendEntity(topic, stackOrders)
        }
      })
    }

    while(true){
      Thread.sleep(1000)
    }
  }
}
