package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.DeliveryOptions
import io.vertx.scala.meetupdemo.httpPort

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * A small HTTP-server that tries to retrieve an answer from 'testAdress' to display it.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class HttpBusVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    vertx
      .createHttpServer()
      .requestHandler(req => {
        vertx.eventBus()
          .sendFuture[String]("testAddress", "who is there?", DeliveryOptions().setSendTimeout(10))
          .onComplete{
            case Failure(t) => req.response().end("Nobody answered")
            case Success(s) => req.response().end(s.body())
          }
      })
      .listenFuture(config.getInteger(httpPort, 8082))
      .map(_ => ())
  }
}
