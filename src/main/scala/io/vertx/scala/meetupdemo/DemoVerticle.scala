package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Promise
import scala.util.{Failure, Success}

class DemoVerticle extends ScalaVerticle {


  override def start(startPromise: Promise[Unit]): Unit = {
    vertx
      .createHttpServer()
      .requestHandler(a => a.response().end("Hello World"))
      .listenFuture(8666)
      .andThen{
        case Success(_) => startPromise.success(())
        case Failure(t) => startPromise.failure(t)
      }

  }
}
