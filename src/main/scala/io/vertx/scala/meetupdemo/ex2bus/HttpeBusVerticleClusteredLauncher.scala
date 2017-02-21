package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.{Vertx, VertxOptions}

/**
  * Launch a clustered Vertx instance for playing around.
  */
object HttpeBusVerticleClusteredLauncher {
  def main(args: Array[String]): Unit = {
    //Reduce heartbeat interval for demo purposes
    System.setProperty("hazelcast.max.no.heartbeat.seconds","5")
    Vertx.clusteredVertx(VertxOptions().setClusterHost("127.0.0.1"),v => {
      val vertx = v.result()
      vertx.deployVerticle(nameForVerticle[HttpBusVerticle])
    })
  }
}
