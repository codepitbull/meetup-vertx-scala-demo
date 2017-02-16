package io.vertx.scala.meetupdemo.ex1bus

import io.vertx.scala.meetupdemo.VerticleTesting
import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class BusVerticleSpec extends VerticleTesting[BusVerticle] with Matchers {

  "BusVerticle" should "reply to a message" in {
    vertx
      .eventBus()
      .sendFuture[String]("testAddress", "msg")
        .map(res => res.body() should equal("Hello World!"))
  }

}
