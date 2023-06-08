package ru.broom.common_trade.service.util

import ru.broom.common_trade.service.util.CommonDateTimeUtils.getLogFormatTimeNow

import java.io.{File, FileOutputStream, PrintStream}

object LogUtils {
  private val out = new FileOutputStream(new File("rf122.log"), true)
  private def time: String = getLogFormatTimeNow
  def log(message: String): Unit = {
    println(message)
    Console.withOut(new PrintStream(out, true, "UTF-8")) {
      println(time + " " + message)
    }
  }

  def logException(e: Exception): Unit = {
    var message = e.getMessage
    if (!e.getStackTrace.isEmpty)
      message = message + "\n" + e.getStackTrace.map(_.toString).reduce((x1,x2)=>x1+"\n"+x2)
    log(message)
  }
}