package ru.broom.common_trade

import java.util.Date
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import ru.broom.common_trade.constants.Constants
import ru.broom.common_trade.service.util.DateTimeUtil

@RunWith(classOf[JUnitRunner])
class DateTimeUtilTest extends AnyFunSuite{

  test("around dates"){
    val date = DateTimeUtil.createAroundDates(new Date(System.currentTimeMillis()), Constants.Candle.Intervals.FIFTY_MINUTES_INTERVAL)
    println(date)
  }

//  test("date test"){
//    val it = DateTimeUtil.createDates(Constants.Candle.Intervals.FIFTY_MINUTES_INTERVAL).iterator
//    while(it.hasNext){
//      var lastDate = it.next()
//      println(lastDate)
//      if (!it.hasNext)
//        while(true){
//          lastDate = DateTimeUtil.createNextDate(lastDate, Constants.Candle.Intervals.FIFTY_MINUTES_INTERVAL)
//          println(lastDate)
//          Thread.sleep(1000)
//        }
//    }
//  }
}
