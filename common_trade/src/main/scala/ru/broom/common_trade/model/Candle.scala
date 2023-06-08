package ru.broom.common_trade.model

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}
import java.sql.Timestamp

/**
 * Subject that buffers all items it observes and replays them to any {@link Observer} that subscribes.
 * <p>
 * <img width="640" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/S.ReplaySubject.png" alt="">
 * <p>
 * Example usage:
 * <p>
 * <pre>
**/

@Entity
@Table(name = "candle")
case class Candle() extends Serializable {

  def this(currency: String, openTimestamp: Timestamp, closeTimestamp: Timestamp, openCost: Double,
           closeCost: Double, minCost: Double, maxCost: Double, volatilityCost: Double, volatilityPercent: Double,
           askStandardDeviationCost: Double, askVarianceCost: Double, askAverageCost: Double, askStandardDeviationQuantity: Double,
           askVarianceQuantity: Double, askSumQuantity: Double, askAverageQuantity: Double, bidStandardDeviationCost: Double, bidVarianceCost: Double,
           bidAverageCost: Double, bidStandardDeviationQuantity: Double, bidVarianceQuantity: Double, bidSumQuantity: Double, bidAverageQuantity: Double,
           buyStandardDeviationCost: Double, buyVarianceCost: Double, buyAverageCost: Double, buyStandardDeviationQuantity: Double, buyVarianceQuantity: Double,
           buySumQuantity: Double, buyAverageQuantity: Double, sellStandardDeviationCost: Double, sellVarianceCost: Double, sellAverageCost: Double,
           sellStandardDeviationQuantity: Double, sellVarianceQuantity: Double, sellSumQuantity: Double, sellAverageQuantity: Double){
    this()
    this.currency=currency
    this.openTimestamp=openTimestamp
    this.closeTimestamp=closeTimestamp
    this.openCost=openCost
    this.closeCost=closeCost
    this.minCost=minCost
    this.maxCost=maxCost
    this.volatilityCost=volatilityCost
    this.volatilityPercent=volatilityPercent
    this.askStandardDeviationCost=askStandardDeviationCost
    this.askVarianceCost=askVarianceCost
    this.askAverageCost=askAverageCost
    this.askStandardDeviationQuantity=askStandardDeviationQuantity
    this.askVarianceQuantity=askVarianceQuantity
    this.askSumQuantity=askSumQuantity
    this.askAverageQuantity=askAverageQuantity
    this.bidStandardDeviationCost=bidStandardDeviationCost
    this.bidVarianceCost=bidVarianceCost
    this.bidAverageCost=bidAverageCost
    this.bidStandardDeviationQuantity=bidStandardDeviationQuantity
    this.bidVarianceQuantity=bidVarianceQuantity
    this.bidSumQuantity=bidSumQuantity
    this.bidAverageQuantity=bidAverageQuantity
    this.buyStandardDeviationCost=buyStandardDeviationCost
    this.buyVarianceCost=buyVarianceCost
    this.buyAverageCost=buyAverageCost
    this.buyStandardDeviationQuantity=buyStandardDeviationQuantity
    this.buyVarianceQuantity=buyVarianceQuantity
    this.buySumQuantity=buySumQuantity
    this.buyAverageQuantity=buyAverageQuantity
    this.sellStandardDeviationCost=sellStandardDeviationCost
    this.sellVarianceCost=sellVarianceCost
    this.sellAverageCost=sellAverageCost
    this.sellStandardDeviationQuantity=sellStandardDeviationQuantity
    this.sellVarianceQuantity=sellVarianceQuantity
    this.sellSumQuantity=sellSumQuantity
    this.sellAverageQuantity=sellAverageQuantity
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _
  @Column(length = 16)
  var currency: String = _
  var openTimestamp: Timestamp = _
  var closeTimestamp: Timestamp = _
  var openCost: Double = _
  var closeCost: Double = _
  var minCost: Double = _
  var maxCost: Double = _
  var volatilityCost: Double = _
  var volatilityPercent: Double = _
  var askStandardDeviationCost: Double = _
  var askVarianceCost: Double = _
  var askAverageCost: Double = _
  var askStandardDeviationQuantity: Double = _
  var askVarianceQuantity: Double = _
  var askSumQuantity: Double = _
  var askAverageQuantity: Double = _
  var bidStandardDeviationCost: Double = _
  var bidVarianceCost: Double = _
  var bidAverageCost: Double = _
  var bidStandardDeviationQuantity: Double = _
  var bidVarianceQuantity: Double = _
  var bidSumQuantity: Double = _
  var bidAverageQuantity: Double = _
  var buyStandardDeviationCost: Double = _
  var buyVarianceCost: Double = _
  var buyAverageCost: Double = _
  var buyStandardDeviationQuantity: Double = _
  var buyVarianceQuantity: Double = _
  var buySumQuantity: Double = _
  var buyAverageQuantity: Double = _
  var sellStandardDeviationCost: Double = _
  var sellVarianceCost: Double = _
  var sellAverageCost: Double = _
  var sellStandardDeviationQuantity: Double = _
  var sellVarianceQuantity: Double = _
  var sellSumQuantity: Double = _
  var sellAverageQuantity: Double = _

  def getCurrency: String = currency
  def getOpenTimestamp: Timestamp = openTimestamp
  def getCloseTimestamp: Timestamp = closeTimestamp
  def getOpenCost: Double = openCost
  def getCloseCost: Double = closeCost
  def getMinCost: Double = minCost
  def getMaxCost: Double = maxCost
  def getVolatilityCost: Double = volatilityCost
  def getVolatilityPercent: Double = volatilityPercent
  def getAskStandardDeviationCost: Double = askStandardDeviationCost
  def getAskVarianceCost: Double = askVarianceCost
  def getAskAverageCost: Double = askAverageCost
  def getAskStandardDeviationQuantity: Double = askStandardDeviationQuantity
  def getAskVarianceQuantity: Double = askVarianceQuantity
  def getAskSumQuantity: Double = askSumQuantity
  def getAskAverageQuantity: Double = askAverageQuantity
  def getBidStandardDeviationCost: Double = bidStandardDeviationCost
  def getBidVarianceCost: Double = bidVarianceCost
  def getBidAverageCost: Double = bidAverageCost
  def getBidStandardDeviationQuantity: Double = bidStandardDeviationQuantity
  def getBidVarianceQuantity: Double = bidVarianceQuantity
  def getBidSumQuantity: Double = bidSumQuantity
  def getBidAverageQuantity: Double = bidAverageQuantity
  def getBuyStandardDeviationCost: Double = buyStandardDeviationCost
  def getBuyVarianceCost: Double = buyVarianceCost
  def getBuyAverageCost: Double = buyAverageCost
  def getBuyStandardDeviationQuantity: Double = buyStandardDeviationQuantity
  def getBuyVarianceQuantity: Double = buyVarianceQuantity
  def getBuySumQuantity: Double = buySumQuantity
  def getBuyAverageQuantity: Double = buyAverageQuantity
  def getSellStandardDeviationCost: Double = sellStandardDeviationCost
  def getSellVarianceCost: Double = sellVarianceCost
  def getSellAverageCost: Double = sellAverageCost
  def getSellStandardDeviationQuantity: Double = sellStandardDeviationQuantity
  def getSellVarianceQuantity: Double = sellVarianceQuantity
  def getSellSumQuantity: Double = sellSumQuantity
  def getSellAverageQuantity: Double = sellAverageQuantity

  def setCurrency(currency: String): Unit = this.currency=currency
  def setOpenTimestamp(openTimestamp: Timestamp): Unit = this.openTimestamp=openTimestamp
  def setCloseTimestamp(closeTimestamp: Timestamp): Unit = this.closeTimestamp=closeTimestamp
  def setOpenCost(openCost: Double): Unit = this.openCost=openCost
  def setCloseCost(closeCost: Double): Unit = this.closeCost=closeCost
  def setMinCost(minCost: Double): Unit = this.minCost=minCost
  def setMaxCost(maxCost: Double): Unit = this.maxCost=maxCost
  def setVolatilityCost(volatilityCost: Double): Unit = this.volatilityCost=volatilityCost
  def setVolatilityPercent(volatilityPercent: Double): Unit = this.volatilityPercent=volatilityPercent
  def setAskStandardDeviationCost(askStandardDeviationCost: Double): Unit = this.askStandardDeviationCost=askStandardDeviationCost
  def setAskVarianceCost(askVarianceCost: Double): Unit = this.askVarianceCost=askVarianceCost
  def setAskAverageCost(askAverageCost: Double): Unit = this.askAverageCost=askAverageCost
  def setAskStandardDeviationQuantity(askStandardDeviationQuantity: Double): Unit = this.askStandardDeviationQuantity=askStandardDeviationQuantity
  def setAskVarianceQuantity(askVarianceQuantity: Double): Unit = this.askVarianceQuantity=askVarianceQuantity
  def setAskSumQuantity(askSumQuantity: Double): Unit = this.askSumQuantity=askSumQuantity
  def setAskAverageQuantity(askAverageQuantity: Double): Unit = this.askAverageQuantity=askAverageQuantity
  def setBidStandardDeviationCost(bidStandardDeviationCost: Double): Unit = this.bidStandardDeviationCost=bidStandardDeviationCost
  def setBidVarianceCost(bidVarianceCost: Double): Unit = this.bidVarianceCost=bidVarianceCost
  def setBidAverageCost(bidAverageCost: Double): Unit = this.bidAverageCost=bidAverageCost
  def setBidStandardDeviationQuantity(bidStandardDeviationQuantity: Double): Unit = this.bidStandardDeviationQuantity=bidStandardDeviationQuantity
  def setBidVarianceQuantity(bidVarianceQuantity: Double): Unit = this.bidVarianceQuantity=bidVarianceQuantity
  def setBidSumQuantity(bidSumQuantity: Double): Unit = this.bidSumQuantity=bidSumQuantity
  def setBidAverageQuantity(bidAverageQuantity: Double): Unit = this.bidAverageQuantity=bidAverageQuantity
  def setBuyStandardDeviationCost(buyStandardDeviationCost: Double): Unit = this.buyStandardDeviationCost=buyStandardDeviationCost
  def setBuyVarianceCost(buyVarianceCost: Double): Unit = this.buyVarianceCost=buyVarianceCost
  def setBuyAverageCost(buyAverageCost: Double): Unit = this.buyAverageCost=buyAverageCost
  def setBuyStandardDeviationQuantity(buyStandardDeviationQuantity: Double): Unit = this.buyStandardDeviationQuantity=buyStandardDeviationQuantity
  def setBuyVarianceQuantity(buyVarianceQuantity: Double): Unit = this.buyVarianceQuantity=buyVarianceQuantity
  def setBuySumQuantity(buySumQuantity: Double): Unit = this.buySumQuantity=buySumQuantity
  def setBuyAverageQuantity(buyAverageQuantity: Double): Unit = this.buyAverageQuantity=buyAverageQuantity
  def setSellStandardDeviationCost(sellStandardDeviationCost: Double): Unit = this.sellStandardDeviationCost=sellStandardDeviationCost
  def setSellVarianceCost(sellVarianceCost: Double): Unit = this.sellVarianceCost=sellVarianceCost
  def setSellAverageCost(sellAverageCost: Double): Unit = this.sellAverageCost=sellAverageCost
  def setSellStandardDeviationQuantity(sellStandardDeviationQuantity: Double): Unit = this.sellStandardDeviationQuantity=sellStandardDeviationQuantity
  def setSellVarianceQuantity(sellVarianceQuantity: Double): Unit = this.sellVarianceQuantity=sellVarianceQuantity
  def setSellSumQuantity(sellSumQuantity: Double): Unit = this.sellSumQuantity=sellSumQuantity
  def setSellAverageQuantity(sellAverageQuantity: Double): Unit = this.sellAverageQuantity=sellAverageQuantity

}
