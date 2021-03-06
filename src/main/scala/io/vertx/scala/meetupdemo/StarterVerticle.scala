package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.core.DeploymentOptions
import io.vertx.scala.meetupdemo.ex1http.HttpVerticle
import io.vertx.scala.meetupdemo.ex3web.TemplateVerticle
import io.vertx.scala.meetupdemo.ex4bridge.BridgeVerticle
import io.vertx.scala.meetupdemo.ex5metrics.MetricsVerticle

import scala.concurrent.Future
import scala.concurrent.Future.sequence

/**
  * Example for a starter verticle that deploys several other verticles.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class StarterVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    sequence(Seq(
      vertx.deployVerticleFuture(nameForVerticle[MetricsVerticle], DeploymentOptions().setConfig(Json.obj((httpPort, 8080)))),
      vertx.deployVerticleFuture(nameForVerticle[TemplateVerticle], DeploymentOptions().setConfig(Json.obj((httpPort, 8083)))),
      vertx.deployVerticleFuture(nameForVerticle[BridgeVerticle], DeploymentOptions().setConfig(Json.obj((httpPort, 8084)))))
    ).map(_ => ())
  }
}

