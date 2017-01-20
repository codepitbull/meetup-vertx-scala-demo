package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.core.DeploymentOptions

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Example for a starter verticle that deploys several other verticles.
  */
class StarterVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    val combinedFuture = for {
      f1 <- vertx.deployVerticleFuture("scala:"+classOf[DemoVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8081))))
      f2 <- vertx.deployVerticleFuture("scala:"+classOf[MetricsVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8082))))
      f3 <- vertx.deployVerticleFuture("scala:"+classOf[TemplateVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8083))))
      f4 <- vertx.deployVerticleFuture("scala:"+classOf[BridgeVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8084))))
    } yield(f1, f2, f3, f4)

    combinedFuture.onComplete{
      case Success(_) => promise.success(())
      case Failure(t) => promise.failure(t)
    }

    promise.future
  }
}

