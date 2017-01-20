package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future._

/**
  * A very simple example for bootstrapping a web-verticle.
  */
class SimpleVerticle extends ScalaVerticle {

  override def start() = {
    val port = Option(config.getInteger(httpPort).intValue()).getOrElse(8080)
    vertx
      .createHttpServer()
      .requestHandler(_.response().end("Hello World"))
      .listen(port)
    successful(())
  }
}
