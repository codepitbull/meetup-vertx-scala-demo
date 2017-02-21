package io.vertx.scala.meetupdemo.ex6mqtt

import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTesting, VerticleTestingAsync, mqttPort}
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttConnectOptions}
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.scalatest.Matchers

import scala.concurrent.Promise

/**
  * Created by jochen on 16.02.17.
  */
class MqttVerticleSpec
  extends VerticleTestingAsync[MqttVerticle] with Matchers {

  override def config(): JsonObject = Json.obj((mqttPort, 1883))

  "Mqtt messages" should "be received and forwarded to mqtt.received" in {
    val promise = Promise[String]
    vertx.eventBus().localConsumer[String]("mqtt.received").handler(res => promise.success(res.body()))
    val sampleClient = new MqttClient("tcp://localhost:1883", "JavaSample", new MemoryPersistence)
    val connOpts = new MqttConnectOptions()
    connOpts.setCleanSession(true)
    sampleClient.connect(connOpts)
    val message = new MqttMessage("hallo welt".getBytes)
    message.setQos(0)
    sampleClient.publish("test", message)
    promise.future.map(res => res should equal("received [hallo welt] from JavaSample"))
  }

}
