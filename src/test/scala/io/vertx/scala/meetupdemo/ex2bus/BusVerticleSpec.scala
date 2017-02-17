package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.scala.meetupdemo.VerticleTestingAsync
import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class BusVerticleSpec extends VerticleTestingAsync[BusVerticle] with Matchers {

  "The testAddress" should "reply 'Hello World!'" in {
    vertx
      .eventBus()
      .sendFuture[String]("testAddress", "msg")
        .map(res => res.body() should not be empty)
  }

}
