package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

class SimpleVerticle extends ScalaVerticle {

  override def start(): Unit = {
    vertx
      .createHttpServer()
      .requestHandler(_.response().end("Hello World"))
      .listen(8666)
  }
}
