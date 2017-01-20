package io.vertx.scala.meetupdemo

import io.vertx.scala.core.{DeploymentOptions, Vertx, VertxOptions}

/**
  * Launch a clustered Vertx instance for playing around.
  */
object VerticleClusteredLauncher {
  def main(args: Array[String]): Unit = {
    //Reduce heartbeat interval for demo purposes
    System.setProperty("hazelcast.max.no.heartbeat.seconds","5")
    Vertx.clusteredVertx(VertxOptions().setClusterHost("127.0.0.1"),v => {
      val vertx = v.result()
      vertx.deployVerticle("scala:"+classOf[DemoVerticle].getName, DeploymentOptions().setInstances(2))
    })
  }
}
