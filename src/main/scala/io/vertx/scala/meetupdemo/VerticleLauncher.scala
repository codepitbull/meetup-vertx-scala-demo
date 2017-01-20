package io.vertx.scala.meetupdemo

import io.vertx.scala.core.Vertx

/**
  * Launch a Vertx instance for playing around.
  */
object VerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle("scala:"+classOf[StarterVerticle].getName)
  }
}
