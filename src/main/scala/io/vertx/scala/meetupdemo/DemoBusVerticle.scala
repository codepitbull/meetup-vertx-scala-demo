package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message

import scala.concurrent.Promise

class DemoBusVerticle extends ScalaVerticle {

  override def start(startPromise: Promise[Unit]): Unit = {
    vertx.eventBus().consumer("req", {a:Message[String] => print(a.body())})
  }
}
