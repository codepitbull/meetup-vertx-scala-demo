package io.vertx.scala.meetupdemo.ex6mqtt

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.Vertx

/**
  * Launch a Vertx instance and deploy MqttVerticle.
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
object MqttVerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[MqttVerticle])
  }
}
