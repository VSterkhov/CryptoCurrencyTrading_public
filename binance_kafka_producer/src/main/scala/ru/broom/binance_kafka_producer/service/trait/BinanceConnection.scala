package ru.broom.binance_kafka_producer.service.`trait`

import com.binance.api.client.exception.BinanceApiException
import com.binance.api.client.{BinanceApiClientFactory, BinanceApiRestClient}
import ru.broom.binance_kafka_producer.config.ModuleProperties.BinanceCredentials._

trait BinanceConnection {
  protected var binanceClient: BinanceApiRestClient = _

  new Thread(() => {
    while (true) {
      try {
        liveClient()
      } catch {
        case e: Exception => {
          Thread.sleep(3000)
          e.printStackTrace()
        }
      }

    }
  }).start()

  private def liveClient(): Unit = {
    try {
      binanceClient.ping()
    } catch {
      case e: NullPointerException => {
        Thread.sleep(10000)
        binanceClient = BinanceApiClientFactory.newInstance(API_KEY, SECRET).newRestClient
      }
      case e: BinanceApiException => {
        Thread.sleep(10000)
        binanceClient = BinanceApiClientFactory.newInstance(API_KEY, SECRET).newRestClient
      }
    }
  }
}
