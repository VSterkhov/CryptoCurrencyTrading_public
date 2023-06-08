package ru.broom.common_trade.model

import java.util.Date
import scala.collection.mutable.ListBuffer

case class TradeVolumePacket() extends Serializable {

  def this(startDate: Date, endDate: Date) = {
    this()
    this.startDate=startDate
    this.endDate=endDate
  }

  var startDate: Date = _
  var endDate: Date = _

  private val priceList: ListBuffer[Price] = new ListBuffer[Price]
  private val askStackList: ListBuffer[StackOrders] = new ListBuffer[StackOrders]
  private val bidStackList: ListBuffer[StackOrders] = new ListBuffer[StackOrders]
  private val sellStackList: ListBuffer[StackOrders] = new ListBuffer[StackOrders]
  private val buyStackList: ListBuffer[StackOrders] = new ListBuffer[StackOrders]

  def appendEntities(price: Price, askStack: StackOrders, bidStack: StackOrders, sellStack: StackOrders, buyStack: StackOrders): Unit = {

    priceList.append(price)
    askStackList.append(askStack)
    bidStackList.append(bidStack)
    sellStackList.append(sellStack)
    buyStackList.append(buyStack)

  }

  def getPriceList: List[Price] = priceList.toList
  def getAskStackList: List[StackOrders] = askStackList.toList
  def getBidStackList: List[StackOrders] = bidStackList.toList
  def getSellStackList: List[StackOrders] = sellStackList.toList
  def getBuyStackList: List[StackOrders] = buyStackList.toList

}
