package ru.broom.aggregator_kafka_consumer.service

import ru.broom.aggregator_kafka_consumer.service.`trait`.CandleTrait
import ru.broom.common_trade.config.CommonTradeProperties.Currencies.PLAYING_CURRENCIES_ARRAY
import ru.broom.common_trade.model.{AggregatedTradeVolume, Price, StackOrders}
import ru.broom.common_trade.service.util.MathUtil
import rx.Observable

class AggregateKafkaService extends CandleTrait {

  val queuesTypes: Array[String] = Array("PRICE_", "ASK_", "BID_", "SELL_", "BUY_")

  val currenciesTopicsAggregatedObserverMap: Map[String, Observable[AggregatedTradeVolume]] = PLAYING_CURRENCIES_ARRAY.map(currency => {

    val priceConsumerThread = new ObservedKafkaConsumerThread[Price](s"PRICE_$currency")
    val askConsumerThread = new ObservedKafkaConsumerThread[StackOrders](s"ASK_$currency")
    val bidConsumerThread = new ObservedKafkaConsumerThread[StackOrders](s"BID_$currency")
    val sellConsumerThread = new ObservedKafkaConsumerThread[StackOrders](s"SELL_$currency")
    val buyConsumerThread = new ObservedKafkaConsumerThread[StackOrders](s"BUY_$currency")

    val observableAggregatedTradeVolume: Observable[AggregatedTradeVolume] =
      Observable.zip(
        priceConsumerThread.observableObject,
        askConsumerThread.observableObject,
        bidConsumerThread.observableObject,
        sellConsumerThread.observableObject,
        buyConsumerThread.observableObject,
        (price: Price, ask: StackOrders, bid: StackOrders, sell: StackOrders, buy: StackOrders) => {

          appendEntitiesToCandle(price.currency, price, ask, bid, sell, buy)

          println(price.currency, price.offsetNumber,ask.offsetNumber,bid.offsetNumber,sell.offsetNumber,buy.offsetNumber)

          val volume = sell.orders.map(_.getQuantity).sum + buy.orders.map(_.getQuantity).sum

          var sellMaxQuantity = 0d
          var sellAvgQuantity = 0d
          val sellQArr = sell.orders.map(_.getQuantity)
          if (sellQArr.length>0) {
            sellMaxQuantity = sellQArr.max
            sellAvgQuantity = MathUtil.mean(sellQArr)
          }

          var buyMaxQuantity = 0d
          var buyAvgQuantity = 0d
          val buyQArr = buy.orders.map(_.getQuantity)
          if (buyQArr.length>0) {
          buyMaxQuantity = buyQArr.max
          buyAvgQuantity = MathUtil.mean(buyQArr)
          }

          var bidMaxQuantity = 0d
          var bidMaxQCost = 0d
          var bidAvgCost = 0d
            if (bid.orders.nonEmpty){
              val mqBid = bid.orders.maxBy(_.getQuantity)
              bidMaxQuantity = mqBid.getQuantity
              bidMaxQCost = mqBid.getCost
              bidAvgCost = MathUtil.mean(bid.orders.map(_.getCost))
          }

          var askMaxQuantity = 0d
          var askMaxQCost = 0d
          var askAvgCost = 0d
          if (ask.orders.nonEmpty){
            val mqAsk = ask.orders.maxBy(_.getQuantity)
            askMaxQuantity = mqAsk.getQuantity
            askMaxQCost = mqAsk.getCost
            askAvgCost = MathUtil.mean(ask.orders.map(_.getCost))

          }

          new AggregatedTradeVolume(
            price.currency,
            price.timestamp,
            price.cost,
            volume,
            sellMaxQuantity,
            sellAvgQuantity,
            buyMaxQuantity,
            buyAvgQuantity,
            bidMaxQuantity,
            bidMaxQCost,
            bidAvgCost,
            askMaxQuantity,
            askMaxQCost,
            askAvgCost
          )
        }
      )
    (currency, observableAggregatedTradeVolume)

  }).toMap

}
