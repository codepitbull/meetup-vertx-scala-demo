package io.vertx.scala.meetupdemo.ex2http

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.meetupdemo.httpPort

import scala.concurrent.Future

/**
  * A small HTTP-server that always sends the same answer.
  */
class HttpVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    vertx
      .createHttpServer()
      .requestHandler(_.response().end("Hello World"))
      .listenFuture(config.getInteger(httpPort, 8080))
      .map(_ => ())
  }
}
