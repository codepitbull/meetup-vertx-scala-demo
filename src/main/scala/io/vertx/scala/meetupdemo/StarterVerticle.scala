package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class StarterVerticle  extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    val combinedFuture = for {
      f1 <- vertx.deployVerticleFuture("scala:"+classOf[DemoVerticle].getName)
      f2 <- vertx.deployVerticleFuture("scala:"+classOf[MetricsVerticle].getName)
      f3 <- vertx.deployVerticleFuture("scala:"+classOf[TemplateVerticle].getName)
    } yield(f1, f2)

    combinedFuture.onComplete{
      case Success(_) => promise.success(())
      case Failure(t) => promise.failure(t)
    }

    promise.future
  }
}

