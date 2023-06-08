package ru.broom.common_trade.model


import java.sql.Timestamp
import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

@Entity
@Table(name = "aggregated_trade_volume")
case class AggregatedTradeVolume() extends Serializable {

  def this(currency: String, timestamp: Timestamp, cost: Double, volume: Double, sellMaxQuantity: Double, sellAvgQuantity: Double,
           buyMaxQuantity: Double, buyAvgQuantity: Double, bidMaxQuantity: Double, bidMaxQCost: Double,
           bidAvgCost: Double, askMaxQuantity: Double, askMaxQCost: Double, askAvgCost: Double) {
    this()
    this.currency=currency
    this.timestamp=timestamp
    this.cost=cost
    this.volume=volume
    this.sellMaxQuantity=sellMaxQuantity
    this.sellAvgQuantity=sellAvgQuantity
    this.buyMaxQuantity=buyMaxQuantity
    this.buyAvgQuantity=buyAvgQuantity
    this.bidMaxQuantity=bidMaxQuantity
    this.bidMaxQCost=bidMaxQCost
    this.bidAvgCost=bidAvgCost
    this.askMaxQuantity=askMaxQuantity
    this.askMaxQCost=askMaxQCost
    this.askAvgCost=askAvgCost
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator="aggregated_trade_volume_sequence")
  var id: Long = _
  @Column(length = 16)
  var currency: String = _
  var timestamp: Timestamp = _
  var cost: Double = _
  var volume: Double = _
  var sellMaxQuantity: Double = _
  var sellAvgQuantity: Double = _
  var buyMaxQuantity: Double = _
  var buyAvgQuantity: Double = _
  var bidMaxQuantity: Double = _
  var bidMaxQCost: Double = _
  var bidAvgCost: Double = _
  var askMaxQuantity: Double = _
  var askMaxQCost: Double = _
  var askAvgCost: Double = _

  def setCurrency(currency: String): Unit = this.currency=currency
  def setTimestamp(timestamp: Timestamp): Unit = this.timestamp=timestamp
  def setCost(cost: Double): Unit = this.cost=cost
  def setVolume(volume: Double): Unit = this.volume=volume
  def setSellMaxQuantity(sellMaxQuantity: Double): Unit = this.sellMaxQuantity=sellMaxQuantity
  def setSellAvgQuantity(sellAvgQuantity: Double): Unit = this.sellAvgQuantity=sellAvgQuantity
  def setBuyMaxQuantity(buyMaxQuantity: Double): Unit = this.buyMaxQuantity=buyMaxQuantity
  def setBuyAvgQuantity(buyAvgQuantity: Double): Unit = this.buyAvgQuantity=buyAvgQuantity
  def setBidMaxQuantity(bidMaxQuantity: Double): Unit = this.bidMaxQuantity=bidMaxQuantity
  def setBidMaxQCost(bidMaxQCost: Double): Unit = this.bidMaxQCost=bidMaxQCost
  def setBidAvgCost(bidAvgCost: Double): Unit = this.bidAvgCost=bidAvgCost
  def setAskMaxQuantity(askMaxQuantity: Double): Unit = this.askMaxQuantity=askMaxQuantity
  def setAskMaxQCost(askMaxQCost: Double): Unit = this.askMaxQCost=askMaxQCost
  def setAskAvgCost(askAvgCost: Double): Unit = this.askAvgCost=askAvgCost

  def getCurrency: String = currency
  def getTimestamp: Timestamp = timestamp
  def getCost: Double = cost
  def getVolume: Double = volume
  def getSellMaxQuantity: Double = sellMaxQuantity
  def getSellAvgQuantity: Double = sellAvgQuantity
  def getBuyMaxQuantity: Double = buyMaxQuantity
  def getBuyAvgQuantity: Double = buyAvgQuantity
  def getBidMaxQuantity: Double = bidMaxQuantity
  def getBidMaxQCost: Double = bidMaxQCost
  def getBidAvgCost: Double = bidAvgCost
  def getAskMaxQuantity: Double = askMaxQuantity
  def getAskMaxQCost: Double = askMaxQCost
  def getAskAvgCost: Double = askAvgCost

}