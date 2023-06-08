package ru.broom.common_trade.model

import java.sql.Timestamp

case class Price() extends Serializable {

  def this(currency: String, timestamp: Timestamp, offsetNumber: Long, cost: Double) {
    this()
    this.currency = currency
    this.timestamp = timestamp
    this.offsetNumber = offsetNumber
    this.cost = cost
  }

  override def toString: String = {
      s"currency: $currency \n" +
      s"timestamp: $timestamp \n" +
      s"offsetNumber: $offsetNumber \n" +
      s"cost: $cost \n"
  }

  var currency: String = _
  var timestamp: Timestamp = _
  var offsetNumber: Long = _
  var cost: Double = _

  def getCurrency: String = currency
  def getTimestamp: Timestamp = timestamp
  def getOffsetNumber: Long = offsetNumber
  def getCost: Double = cost

  def setCurrency(currency: String): Unit = this.currency = currency
  def setTimestamp(timestamp: Timestamp): Unit = this.timestamp = timestamp
  def setOffsetNumber(offsetNumber: Long): Unit = this.offsetNumber=offsetNumber
  def setCost(cost: Double): Unit = this.cost = cost

}
