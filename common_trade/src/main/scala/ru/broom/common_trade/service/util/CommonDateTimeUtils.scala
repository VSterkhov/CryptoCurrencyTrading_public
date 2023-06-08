package ru.broom.common_trade.service.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

object CommonDateTimeUtils {

  def getTimeNow: Date = new Date(System.currentTimeMillis())
  def getLogFormatTimeNow(): String = {
    val dateFormat = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss")
    dateFormat.format(new Date (System.currentTimeMillis()))
  }

  def getDateToday: Date = {
    getTimeOfDay(0,0)
  }

  def getDateYesterday: Date = {
    val calendar = Calendar.getInstance()
    calendar.setTime(new Date(System.currentTimeMillis()))
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    calendar.getTime
  }

  def getDayOfWeek: Int = {
    val calendar = Calendar.getInstance()
    calendar.setTime(new Date(System.currentTimeMillis()))
    calendar.get(Calendar.DAY_OF_WEEK)
  }

  def getTimeOfDay(hour: Int, min: Int): Date = {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, min)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.getTime
  }

  def getOneThirdTime(startTime: Date, endTime: Date): Date = {
    val calOut = Calendar.getInstance()
    val calStart = Calendar.getInstance()
    val calEnd = Calendar.getInstance()
    calStart.setTime(startTime)
    calEnd.setTime(endTime)

    val startMin = calStart.get(Calendar.HOUR_OF_DAY) * 60 + calStart.get(Calendar.MINUTE)
    val endMin = calEnd.get(Calendar.HOUR_OF_DAY) * 60 + calEnd.get(Calendar.MINUTE)

    val oneThirdDifMin = (endMin - startMin) / 3
    val outHour = oneThirdDifMin / 60
    val outMin = oneThirdDifMin - (outHour * 60)

    calOut.setTime(startTime)
    calOut.set(Calendar.HOUR_OF_DAY, calStart.get(Calendar.HOUR_OF_DAY) + outHour)
    calOut.set(Calendar.MINUTE, calStart.get(Calendar.MINUTE) + outMin)
    calOut.getTime
  }
}