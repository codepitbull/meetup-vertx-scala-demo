package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.core.DeploymentOptions
import io.vertx.scala.meetupdemo.ex2http.HttpVerticle
import io.vertx.scala.meetupdemo.ex3web.TemplateVerticle
import io.vertx.scala.meetupdemo.ex4bridge.BridgeVerticle
import io.vertx.scala.meetupdemo.ex5metrics.MetricsVerticle

import scala.concurrent.Future.sequence
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Example for a starter verticle that deploys several other verticles.
  */
class StarterVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val promise = Promise[Unit]
    val futSeq = sequence(Seq(
      vertx.deployVerticleFuture("scala:"+classOf[HttpVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8081)))),
      vertx.deployVerticleFuture("scala:"+classOf[MetricsVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8082)))),
      vertx.deployVerticleFuture("scala:"+classOf[TemplateVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8083)))),
      vertx.deployVerticleFuture("scala:"+classOf[BridgeVerticle].getName, DeploymentOptions().setConfig(Json.obj((httpPort, 8084)))))
    )

    futSeq.onComplete{
      case Success(_) => promise.success(())
      case Failure(t) => promise.failure(t)
    }

    promise.future
  }
}

