package io.vertx.scala.meetupdemo

import io.vertx.scala.core.Vertx

object VerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle("scala:"+classOf[DemoVerticle].getName)
  }
}
