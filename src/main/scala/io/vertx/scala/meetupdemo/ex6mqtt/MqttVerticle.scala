package io.vertx.scala.meetupdemo.ex6mqtt

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.mqtt.MqttServer

import scala.concurrent.Future

/**
  * A VERY small MQTT-server example.
  */
class MqttVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val mqttServer = MqttServer.create(vertx)
    mqttServer.endpointHandler(endpoint => {
      println(s"CLIENT ${endpoint.clientIdentifier()}")

      endpoint.publishHandler(message => {
        println(s"message ${message.payload().toString()}")
      })

      endpoint.accept(false)
    })

    mqttServer.listenFuture().map(_ => ())
  }
}
