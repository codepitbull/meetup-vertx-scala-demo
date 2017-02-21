package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future

/**
  * Simple verticle that replies to the address 'testAddress'.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class BusVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(_.reply(hashCode().toString))
      .completionFuture()
  }
}
