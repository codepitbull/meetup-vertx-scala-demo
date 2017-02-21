package io.vertx.scala.meetupdemo.ex3web

import io.restassured.RestAssured._
import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTesting, httpPort}
import org.hamcrest.CoreMatchers.containsString
import org.scalatest.Matchers

/**
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class TemplateVerticleSpec extends VerticleTesting[TemplateVerticle] with Matchers {

  port = 8666

  override def config(): JsonObject = Json.obj((httpPort, port))

  "A request to /templates/test2.hbs" should "answer with HTML containing 'Template2'" in {
    expect statusCode (200) and() body (containsString("Template2")) when() get ("/templates/test2.hbs")
  }

  "A request to /" should "answer with HTML containing 'TestVar'" in  {
    expect statusCode (200) and() body (containsString("TestVar")) when() get ("/")
  }

}
