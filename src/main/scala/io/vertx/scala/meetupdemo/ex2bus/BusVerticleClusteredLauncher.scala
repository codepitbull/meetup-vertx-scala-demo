package io.vertx.scala.meetupdemo.ex2bus

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.{DeploymentOptions, Vertx, VertxOptions}

/**
  * Launch a clustered Vertx instance and deploy BusVerticle
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
object BusVerticleClusteredLauncher {
  def main(args: Array[String]): Unit = {
    //Reduce heartbeat interval for demo purposes
    System.setProperty("hazelcast.max.no.heartbeat.seconds", "5")
    Vertx.clusteredVertx(VertxOptions().setClusterHost("127.0.0.1"), v => {
      val vertx = v.result()
      vertx.deployVerticle(nameForVerticle[BusVerticle], DeploymentOptions().setInstances(2))
    })
  }
}
