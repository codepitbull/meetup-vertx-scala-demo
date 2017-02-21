package io.vertx.scala.meetupdemo.ex1http

import io.vertx.lang.scala.json.{Json, JsonObject}
import io.vertx.scala.meetupdemo.{VerticleTestingAsync, httpPort}
import org.scalatest._

import scala.concurrent.Promise
import scala.util.Success

/**
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class HttpVerticleSpec extends VerticleTestingAsync[HttpVerticle] with Matchers {

  override def config(): JsonObject = Json.obj((httpPort, 8665))

  "A request to /hello" should "should answer with 'Hello World'" in {
    val promise = Promise[String]
    vertx.createHttpClient()
      .getNow(config().getInteger(httpPort), "127.0.0.1", "/", r => r.bodyHandler(body => promise.complete(Success(body.toString()))))
    promise.future.map(res => res should equal("Hello World"))
  }

}
