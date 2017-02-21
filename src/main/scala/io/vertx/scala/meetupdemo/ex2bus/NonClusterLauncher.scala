package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.{DeploymentOptions, Vertx}

/**
  * Launch a Vertx instance for playing around.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
object NonClusterLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[HttpBusVerticle])
    vertx.deployVerticle(nameForVerticle[BusVerticle])
  }
}
