package ru.broom.hibernate_postgres_common.service.util

import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

object HibernateSessionFactory {
  private var sessionFactory: SessionFactory = buildSessionFactory()

  protected def buildSessionFactory(): SessionFactory = {
    val registry = new StandardServiceRegistryBuilder().configure().build()
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory()
    } catch {
      case e: Exception =>
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        throw new ExceptionInInitializerError("Initial SessionFactory failed" + e)
    }
    sessionFactory
  }

  def getSessionFactory: SessionFactory = sessionFactory

  def shutdown(): Unit = getSessionFactory.close()

}
