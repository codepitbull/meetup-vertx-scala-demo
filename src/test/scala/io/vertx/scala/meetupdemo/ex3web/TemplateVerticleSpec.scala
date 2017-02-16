package io.vertx.scala.meetupdemo.ex3web

import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTesting, httpPort}
import org.scalatest.Matchers

import scala.concurrent.Promise
import scala.util.Success

/**
  * Created by jochen on 16.02.17.
  */
class TemplateVerticleSpec extends VerticleTesting[TemplateVerticle] with Matchers {

  override def config(): JsonObject = Json.obj((httpPort, 8667))

  "HttpVerticle" should "answer with HTML containing 'TestVar' to a request for /" in {
    val promise = Promise[String]
    vertx.createHttpClient()
      .getNow(config().getInteger(httpPort), "127.0.0.1", "/", r => r.bodyHandler(body => promise.complete(Success(body.toString()))))
    promise.future.map(res => res should include("TestVar"))
  }

  "HttpVerticle" should "answer with HTML containing 'Template2' to a request for /templates/test2.hbs" in {
    val promise = Promise[String]
    vertx.createHttpClient()
      .getNow(config().getInteger(httpPort), "127.0.0.1", "/templates/test2.hbs", r => r.bodyHandler(body => promise.complete(Success(body.toString()))))
    promise.future.map(res => res should include("Template2"))
  }

}
