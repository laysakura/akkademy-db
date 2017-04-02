package com.github.laysakura.akkademy

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.github.laysakura.akkademy.messages.exceptions.KeyNotFoundException
import com.github.laysakura.akkademy.messages.{GetRequest, SetRequest}

import scala.collection.mutable

class AkkademyDb extends Actor {
  private val logger = Logging(context.system, this)

  private val map = mutable.Map[String, Any]()

  override def receive: Actor.Receive = {
    case GetRequest(key) => {
      logger.info("received GetRequest - key: {}", key)
      map.get(key) match {
        case Some(v) => sender ! v
        case None => sender ! Status.Failure(KeyNotFoundException(key))
      }
    }
    case SetRequest(key, value) => {
      logger.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => logger.warning("received unknown message: {}", o)
  }
}
