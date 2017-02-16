package io.vertx.scala.meetupdemo.ex2http

import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTesting, httpPort}
import org.scalatest._

import scala.concurrent.Promise
import scala.util.Success

class HttpVerticleSpec extends VerticleTesting[HttpVerticle] with Matchers {

  override def config(): JsonObject = Json.obj((httpPort, 8666))

  "HttpVerticle" should "answer with 'Hello World' when /hello is accessed" in {
    val promise = Promise[String]
    vertx.createHttpClient()
      .getNow(config().getInteger(httpPort), "127.0.0.1", "/hello", r => r.bodyHandler(body => promise.complete(Success(body.toString()))))
    promise.future.map(res => res should equal("Hello World"))
  }

}
