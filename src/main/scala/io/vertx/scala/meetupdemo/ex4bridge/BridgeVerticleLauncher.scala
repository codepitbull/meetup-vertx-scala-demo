package io.vertx.scala.meetupdemo.ex4bridge

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.Vertx

/**
  * Launch a Vertx instance and deploy BridgeVerticle.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
object BridgeVerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[BridgeVerticle])
  }
}
