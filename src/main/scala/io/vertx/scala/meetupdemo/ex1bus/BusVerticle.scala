package io.vertx.scala.meetupdemo.ex1bus

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future

/**
  * Simple verticle that replies to the address 'testAddress'.
  */
class BusVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(_.reply("Hello World!"))
      .completionFuture()
  }
}
