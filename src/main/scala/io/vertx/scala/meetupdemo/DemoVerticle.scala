package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class DemoVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val port = config.getInteger(httpPort, 8080)
    val promise = Promise[Unit]
    vertx
      .createHttpServer()
      .requestHandler(_.response().end("Hello World"))
      .listenFuture(port)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }
}
