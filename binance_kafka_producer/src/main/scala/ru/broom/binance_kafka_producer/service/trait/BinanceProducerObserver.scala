package ru.broom.binance_kafka_producer.service.`trait`

import com.binance.api.client.domain.market.{OrderBook, TickerPrice}
import ru.broom.binance_kafka_producer.config.ModuleProperties.AskBinance._
import ru.broom.common_trade.model.{Order, Price, StackOrders}
import ru.broom.common_trade.config.CommonTradeProperties.Currencies.PLAYING_CURRENCIES_ARRAY

import scala.collection.mutable.ListBuffer
import rx.subjects.BehaviorSubject

import java.sql.Timestamp
import java.util.Date
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

trait BinanceProducerObserver extends BinanceConnection {

  protected val currenciesPriceObserverMap: Map[String, BehaviorSubject[Price]] =
    PLAYING_CURRENCIES_ARRAY.map(currency=>{("PRICE_"+currency, BehaviorSubject.create[Price]())}).toMap

  protected val bidCallOrdersObserverMap: Map[String, BehaviorSubject[StackOrders]] =
    PLAYING_CURRENCIES_ARRAY.map(currency=>{("BID_"+currency, BehaviorSubject.create[StackOrders]())}).toMap

  protected val askCallOrdersObserverMap: Map[String, BehaviorSubject[StackOrders]] =
    PLAYING_CURRENCIES_ARRAY.map(currency=>{("ASK_"+currency, BehaviorSubject.create[StackOrders]())}).toMap

  protected val sellPerfectOrdersObserverMap: Map[String, BehaviorSubject[StackOrders]] =
    PLAYING_CURRENCIES_ARRAY.map(currency=>{("SELL_"+currency, BehaviorSubject.create[StackOrders]())}).toMap

  protected val buyPerfectOrdersObserverMap: Map[String, BehaviorSubject[StackOrders]] =
    PLAYING_CURRENCIES_ARRAY.map(currency=>{("BUY_"+currency, BehaviorSubject.create[StackOrders]())}).toMap

  var offsetNumber = 1L

  new Thread(() => {
    Thread.sleep(10000)
    while(true) {

      new Thread(()=>{
        try {

          val nowMs = System.currentTimeMillis()
          val nowTimestamp = new Timestamp(nowMs)
          val priceMap = binanceClient.getAllPrices.map(ticker => {
            (ticker.getSymbol, new Price(ticker.getSymbol, nowTimestamp, offsetNumber, ticker.getPrice.toDouble))
          }).toMap

          for (currency <- PLAYING_CURRENCIES_ARRAY) {

            new Thread(() => {
              try {
                val priceBehaviorSubject = currenciesPriceObserverMap("PRICE_" + currency)
                val bidCallBehaviorSubject = bidCallOrdersObserverMap("BID_" + currency)
                val askCallBehaviorSubject = askCallOrdersObserverMap("ASK_" + currency)
                val sellPerfectOrdersBehaviorSubject = sellPerfectOrdersObserverMap("SELL_" + currency)
                val buyPerfectOrdersBehaviorSubject = buyPerfectOrdersObserverMap("BUY_" + currency)

                val orderBook: OrderBook = binanceClient.getOrderBook(currency, CALL_ORDER_ASK_STACK_COUNT)

                val bids = orderBook.getBids map (x => {
                  new Order(x.getPrice.toDouble, x.getQty.toDouble)
                })
                val asks = orderBook.getAsks map (x => {
                  new Order(x.getPrice.toDouble, x.getQty.toDouble)
                })

                val aggTrades = binanceClient.getAggTrades(currency, null, PERFECT_ORDER_ASK_STACK_COUNT, nowMs - TIMEOUT_MILLISECONDS, nowMs)

                val sellOrderList = new ListBuffer[Order]
                val buyOrderList = new ListBuffer[Order]
                aggTrades.forEach(trade => {
                  val order = new Order(trade.getPrice.toDouble, trade.getQuantity.toDouble)
                  if (trade.isBuyerMaker)
                    sellOrderList.append(order)
                  else
                    buyOrderList.append(order)
                })

                priceBehaviorSubject.onNext(priceMap(currency))
                sellPerfectOrdersBehaviorSubject.onNext(new StackOrders(currency, nowTimestamp, offsetNumber, sellOrderList.toArray))
                buyPerfectOrdersBehaviorSubject.onNext(new StackOrders(currency, nowTimestamp, offsetNumber, buyOrderList.toArray))
                askCallBehaviorSubject.onNext(new StackOrders(currency, nowTimestamp, offsetNumber, asks.toArray))
                bidCallBehaviorSubject.onNext(new StackOrders(currency, nowTimestamp, offsetNumber, bids.toArray))
              } catch {
                case e: Exception => e.printStackTrace()
              }
            }).start()
          }
        } catch {
          case e: Exception => e.printStackTrace()
        }

      }).start()

      offsetNumber=offsetNumber+1
      Thread.sleep(TIMEOUT_MILLISECONDS)

    }
  }).start()

}



