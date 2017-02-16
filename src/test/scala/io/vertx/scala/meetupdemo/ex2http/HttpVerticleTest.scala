package io.vertx.scala.meetupdemo.ex2http

import io.restassured.RestAssured
import io.restassured.RestAssured._
import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTesting, httpPort}
import org.hamcrest.CoreMatchers.equalTo
import org.scalatest._


class HttpVerticleTest extends VerticleTesting[HttpVerticle] with Matchers {

  baseURI = "http://localhost"
  port = 8666

  override def config(): JsonObject = Json.obj((httpPort, 8666))

  "HttpVerticle" should "should be alive" in {
    RestAssured.expect
      .statusCode(200)
      .body(equalTo("Hello World"))
    .when.get("/hello")

    Thread.sleep(10000)
    null
  }

}
