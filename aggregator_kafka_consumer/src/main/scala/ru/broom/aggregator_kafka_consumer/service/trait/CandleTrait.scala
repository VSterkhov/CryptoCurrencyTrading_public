package ru.broom.aggregator_kafka_consumer.service.`trait`

import ru.broom.common_trade.config.CommonTradeProperties.Currencies.PLAYING_CURRENCIES_ARRAY
import ru.broom.common_trade.constants.Constants
import ru.broom.common_trade.model.{Candle, Price, StackOrders, TradeVolumePacket}
import ru.broom.common_trade.service.util.{DateTimeUtil, MathUtil}
import ru.broom.hibernate_postgres_common.service.CandleDao

import java.sql.Timestamp
import java.util.Date

trait CandleTrait {
  private var swimDates: (Date, Date) = createFiftyMinutesSwimDates()
  private var tradeVolumePacketMap: Map[String, TradeVolumePacket] = PLAYING_CURRENCIES_ARRAY.map(currency=>{(currency, new TradeVolumePacket(swimDates._1, swimDates._2))}).toMap

  new Thread(()=>{
    while(true){
      if(DateTimeUtil.getTimeNow.after(swimDates._2)){
        saveCandles()
        setNewTime()
      }
      Thread.sleep(1000)
    }
  }).start()

  protected def appendEntitiesToCandle(currency: String, price: Price, askStack: StackOrders, bidStack: StackOrders, sellStack: StackOrders, buyStack: StackOrders): Unit = {
    val tradeVolumePacket = tradeVolumePacketMap(currency)
    if (DateTimeUtil.getTimeNow.before(tradeVolumePacket.endDate))
      tradeVolumePacket.appendEntities(price,askStack,bidStack,sellStack,buyStack)
  }

  private def setNewTime(): Unit = {
    swimDates = createFiftyMinutesSwimDates()
    tradeVolumePacketMap = PLAYING_CURRENCIES_ARRAY.map(currency=>{(currency, new TradeVolumePacket(swimDates._1, swimDates._2))}).toMap
  }

  private def createFiftyMinutesSwimDates(): (Date, Date) = {
    DateTimeUtil.createAroundDates(DateTimeUtil.getTimeNow, Constants.Candle.Intervals.FIFTY_MINUTES_INTERVAL)
  }

  private def saveCandles(): Unit = {
    val openTimestamp = new Timestamp(swimDates._1.getTime)
    val closeTimestamp = new Timestamp(swimDates._2.getTime)

    tradeVolumePacketMap.foreach(e=>{
      new Thread(()=>{
        val currency = e._1
        val tradeVolumePacket = e._2
        val priceList = tradeVolumePacket.getPriceList
        val askStackList = tradeVolumePacket.getAskStackList
        val bidStackList = tradeVolumePacket.getBidStackList
        val sellStackList = tradeVolumePacket.getSellStackList
        val buyStackList = tradeVolumePacket.getBuyStackList

        val openCost = priceList.head.getCost
        val closeCost = priceList.last.getCost
        val costList = priceList.map(_.getCost)
        val avgCost = MathUtil.mean(costList)
        val minCost = costList.min
        val maxCost = costList.max
        val volatilityCost = maxCost - minCost
        val volatilityPercent = volatilityCost / (avgCost / 100)

        val askCosts = askStackList.map(_.getOrders.map(_.getCost)).flatMap(_.toList)

        val askStandardDeviationCost = MathUtil.stdDev(askCosts)
        val askVarianceCost = MathUtil.variance(askCosts)
        val askAverageCost = MathUtil.mean(askCosts)

        val askQuantities = askStackList.map(_.getOrders.map(_.getQuantity)).flatMap(_.toList)

        val askStandardDeviationQuantity = MathUtil.stdDev(askQuantities)
        val askVarianceQuantity = MathUtil.variance(askQuantities)
        val askSumQuantity = askQuantities.sum
        val askAverageQuantity = MathUtil.mean(askQuantities)

        val bidCosts = bidStackList.map(_.getOrders.map(_.getCost)).flatMap(_.toList)

        val bidStandardDeviationCost = MathUtil.stdDev(bidCosts)
        val bidVarianceCost = MathUtil.variance(bidCosts)
        val bidAverageCost = MathUtil.mean(bidCosts)

        val bidQuantities = bidStackList.map(_.getOrders.map(_.getQuantity)).flatMap(_.toList)

        val bidStandardDeviationQuantity = MathUtil.stdDev(bidQuantities)
        val bidVarianceQuantity = MathUtil.variance(bidQuantities)
        val bidSumQuantity = bidQuantities.sum
        val bidAverageQuantity = MathUtil.mean(bidQuantities)

        val buyCosts = buyStackList.map(_.getOrders.map(_.getCost)).flatMap(_.toList)

        val buyStandardDeviationCost = MathUtil.stdDev(buyCosts)
        val buyVarianceCost = MathUtil.variance(buyCosts)
        val buyAverageCost = MathUtil.mean(buyCosts)

        val buyQuantities = buyStackList.map(_.getOrders.map(_.getQuantity)).flatMap(_.toList)

        val buyStandardDeviationQuantity = MathUtil.stdDev(buyQuantities)
        val buyVarianceQuantity = MathUtil.variance(buyQuantities)
        val buySumQuantity = buyQuantities.sum
        val buyAverageQuantity = MathUtil.mean(buyQuantities)

        val sellCosts = sellStackList.map(_.getOrders.map(_.getCost)).flatMap(_.toList)

        val sellStandardDeviationCost = MathUtil.stdDev(sellCosts)
        val sellVarianceCost = MathUtil.variance(sellCosts)
        val sellAverageCost = MathUtil.mean(sellCosts)

        val sellQuantities = sellStackList.map(_.getOrders.map(_.getQuantity)).flatMap(_.toList)

        val sellStandardDeviationQuantity = MathUtil.stdDev(sellQuantities)
        val sellVarianceQuantity = MathUtil.variance(sellQuantities)
        val sellSumQuantity = sellQuantities.sum
        val sellAverageQuantity = MathUtil.mean(sellQuantities)

        val candle = new Candle(currency, openTimestamp, closeTimestamp, openCost, closeCost, minCost, maxCost, volatilityCost, volatilityPercent,
          askStandardDeviationCost, askVarianceCost, askAverageCost, askStandardDeviationQuantity,askVarianceQuantity, askSumQuantity,
          askAverageQuantity, bidStandardDeviationCost, bidVarianceCost, bidAverageCost, bidStandardDeviationQuantity, bidVarianceQuantity,
          bidSumQuantity, bidAverageQuantity, buyStandardDeviationCost, buyVarianceCost, buyAverageCost, buyStandardDeviationQuantity, buyVarianceQuantity,
          buySumQuantity, buyAverageQuantity, sellStandardDeviationCost, sellVarianceCost, sellAverageCost, sellStandardDeviationQuantity, sellVarianceQuantity,
          sellSumQuantity, sellAverageQuantity)

        val candleDao = new CandleDao()
        candleDao.persist(candle)

      }).start()

    })
  }

}
