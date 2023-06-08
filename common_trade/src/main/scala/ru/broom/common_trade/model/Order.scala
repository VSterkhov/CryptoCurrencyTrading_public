package ru.broom.common_trade.model

case class Order() extends Serializable {
  def this(cost: Double, quantity: Double) {
    this()
    this.cost=cost
    this.quantity=quantity
  }

  var cost: Double = _
  var quantity: Double = _

  def setCost(cost: Double): Unit = this.cost=cost
  def setQuantity(quantity: Double): Unit = this.quantity=quantity

  def getCost: Double = cost
  def getQuantity: Double = quantity

}
