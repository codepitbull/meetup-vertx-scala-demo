package io.vertx.scala.meetupdemo.ex3web

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.TemplateHandler
import io.vertx.scala.ext.web.templ.HandlebarsTemplateEngine
import io.vertx.scala.meetupdemo.httpPort

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Examples for using templating engines.
  */
class TemplateVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val promise = Promise[Unit]
    val templateEngine = HandlebarsTemplateEngine.create()
    val port = Option(config.getInteger(httpPort).intValue()).getOrElse(8080)
    val router = Router.router(vertx)
    //load templates by automatically resolving them
    router.get("/templates/*").handler(TemplateHandler.create(templateEngine, "io/vertx/scala/meetupdemo/templates/", "text/html"))
    //render template explicitly
    router.get("/").handler(r => templateEngine.renderFuture(r, "io/vertx/scala/meetupdemo/ex3web/templates/test.hbs").andThen{
      case Success(f) => r.response().end(f)
      case Failure(t) => r.response().end(t.getMessage)
    })

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(port)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }
}
