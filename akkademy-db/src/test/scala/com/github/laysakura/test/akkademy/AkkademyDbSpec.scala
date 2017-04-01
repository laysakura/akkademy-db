package com.github.laysakura.test.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.github.laysakura.akkademy.AkkademyDb
import com.github.laysakura.akkademy.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}
