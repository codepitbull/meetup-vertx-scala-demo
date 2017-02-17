package io.vertx.scala.meetupdemo.ex6mqtt

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.meetupdemo.mqttPort
import io.vertx.scala.mqtt.MqttServer

import scala.concurrent.Future

/**
  * A VERY small MQTT-server example.
  */
class MqttVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val port = config.getInteger(mqttPort, 1883)
    val mqttServer = MqttServer.create(vertx)
    mqttServer.endpointHandler(endpoint => {
      endpoint.publishHandler(message => {
        vertx.eventBus().send("mqtt.received", s"received [${message.payload().toString()}] from ${endpoint.clientIdentifier()}")
      })
      endpoint.accept(false)
    })
    mqttServer.listenFuture(port).map(_ => ())
  }
}
