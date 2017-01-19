package io.vertx.scala.meetupdemo

import io.vertx.scala.core.{Vertx, VertxOptions}

object VerticleClusteredLauncher2 {
  def main(args: Array[String]): Unit = {
    //"-Dhazelcast.max.no.heartbeat.seconds=5"
    System.setProperty("hazelcast.max.no.heartbeat.seconds","5")
    Vertx.clusteredVertx(VertxOptions().setClusterHost("127.0.0.1"),v => {
      val vertx = v.result()
      vertx.deployVerticle("scala:"+classOf[DemoBusVerticle].getName)
    })
  }
}
