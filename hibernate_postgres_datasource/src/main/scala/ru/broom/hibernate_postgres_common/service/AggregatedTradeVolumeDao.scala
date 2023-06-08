package ru.broom.hibernate_postgres_common.service

import ru.broom.common_trade.model.AggregatedTradeVolume
import ru.broom.hibernate_postgres_common.service.exception.HibernateDaoException

import scala.util.{Failure, Success, Try}

class AggregatedTradeVolumeDao extends AbstractDao[AggregatedTradeVolume, Long] {

  override def persist(entity: AggregatedTradeVolume): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.persist(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }

  override def findById(id: Long): AggregatedTradeVolume = {
    Try(sessionFactory.openSession()) match {
      case Success(session) => session.find(AggregatedTradeVolume.getClass, id).asInstanceOf[AggregatedTradeVolume]
      case Failure(exception) => throw HibernateDaoException("Exception with AggregatedTradeVolumeDao", exception)
    }
  }

  override def update(entity: AggregatedTradeVolume): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.merge(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }

  override def delete(entity: AggregatedTradeVolume): Unit = {
    Try(sessionFactory.openSession()) match {
      case Success(session) =>
        val transaction = session.beginTransaction
        session.remove(entity)
        transaction.commit()
      case Failure(exception) => exception.printStackTrace()
    }
  }
}
