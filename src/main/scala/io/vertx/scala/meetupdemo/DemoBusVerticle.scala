package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message

import scala.concurrent.Future

class DemoBusVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    vertx.eventBus().consumer("req", {a:Message[String] => print(s"${a.body()} ${hashCode()}")})
    Future.successful(())
  }
}
