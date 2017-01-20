package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class DemoVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    vertx
      .createHttpServer()
      .requestHandler(a => {
        vertx.eventBus().send("req", "Hello")
        a.response().end("Hello World")
      })
      .listenFuture(8666)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }
}
