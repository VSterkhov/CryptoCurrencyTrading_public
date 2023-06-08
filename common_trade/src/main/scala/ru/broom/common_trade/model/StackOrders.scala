package ru.broom.common_trade.model

import java.sql.Timestamp

case class StackOrders() extends Serializable {

  def this(currency: String, timestamp: Timestamp, offsetNumber: Long, orders: Array[Order]) {
    this()
    this.currency=currency
    this.timestamp=timestamp
    this.offsetNumber=offsetNumber
    this.orders=orders
  }

  override def toString: String = {
      s"currency: $currency \n" +
      s"timestamp: $timestamp \n" +
      s"offsetNumber: $offsetNumber \n" +
      s"orders: $orders \n"
  }

  var currency: String = _
  var timestamp: Timestamp = _
  var offsetNumber: Long = _
  var orders: Array[Order] = _

  def getCurrency: String = currency
  def getTimestamp: Timestamp = timestamp
  def getOffsetNumber: Long = offsetNumber
  def getOrders: Array[Order] = orders

  def setCurrency(currency: String): Unit = this.currency=currency
  def setTimestamp(timestamp: Timestamp): Unit = this.timestamp=timestamp
  def setOffsetNumber(offsetNumber: Long): Unit = this.offsetNumber=offsetNumber
  def setOrders(orders: Array[Order]): Unit = this.orders=orders

}
