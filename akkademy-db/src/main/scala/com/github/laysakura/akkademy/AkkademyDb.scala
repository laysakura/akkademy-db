package com.github.laysakura.akkademy

import akka.actor.Actor
import akka.event.Logging
import com.github.laysakura.akkademy.messages.SetRequest

import scala.collection.mutable

class AkkademyDb extends Actor {
  private val logger = Logging(context.system, this)

  val map = mutable.Map[String, Any]()

  override def receive: Actor.Receive = {
    case SetRequest(key, value) => {
      logger.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => logger.info("received unknown message: {}", o)
  }
}
