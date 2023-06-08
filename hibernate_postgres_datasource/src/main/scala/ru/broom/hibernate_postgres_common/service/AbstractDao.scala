package ru.broom.hibernate_postgres_common.service

import org.hibernate.SessionFactory
import ru.broom.hibernate_postgres_common.service.util.HibernateSessionFactory

abstract class AbstractDao[O, I] {

  protected val sessionFactory: SessionFactory = HibernateSessionFactory.getSessionFactory

  def persist(entity: O): Unit
  def findById(id: I): O
  def update(entity: O): Unit
  def delete(entity: O): Unit

}
