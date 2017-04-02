package com.github.laysakura.test.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.github.laysakura.akkademy.AkkademyDb
import com.github.laysakura.akkademy.messages.{GetRequest, SetRequest}
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("AkkademyDb") {
    val actorRef = TestActorRef(new AkkademyDb)

    describe("given SetRequest message") {
      it("should successfully ahsend key/value") {
        actorRef ! SetRequest("key", "value")
      }
    }

    describe("given GetRequest message") {
      describe("""key="key1" is set""") {
        actorRef ! SetRequest("key1", "value1")

        it("should get value1 by key1") {
          val res = Await.result(actorRef ? GetRequest("key1"), 1 second)
          assert(res == "value1")
        }
      }

    }

  }
}
