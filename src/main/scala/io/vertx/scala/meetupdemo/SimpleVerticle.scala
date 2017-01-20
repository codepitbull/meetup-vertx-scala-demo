package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future._

class SimpleVerticle extends ScalaVerticle {

  override def start() = {
    vertx
      .createHttpServer()
      .requestHandler(_.response().end("Hello World"))
      .listen(8666)
    successful(())
  }
}
