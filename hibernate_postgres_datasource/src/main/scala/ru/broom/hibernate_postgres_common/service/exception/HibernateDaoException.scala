package ru.broom.hibernate_postgres_common.service.exception

case class HibernateDaoException(private val message: String = "",
                                 private val cause: Throwable = None.orNull) extends Exception(message, cause)