package ru.broom.hibernate_postgres_common.service

import ru.broom.common_trade.model.{AggregatedTradeVolume, Candle}
import ru.broom.hibernate_postgres_common.service.exception.HibernateDaoException

import scala.util.{Failure, Success, Try}

class CandleDao extends AbstractDao[Candle, Long] {

  override def persist(entity: Candle): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.persist(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }

  override def findById(id: Long): Candle = {
    Try(sessionFactory.openSession()) match {
      case Success(session) => session.find(Candle.getClass, id).asInstanceOf[Candle]
      case Failure(exception) => throw HibernateDaoException("Exception with AggregatedTradeVolumeDao", exception)
    }
  }

  override def update(entity: Candle): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.merge(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }

  override def delete(entity: Candle): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.remove(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }
}
