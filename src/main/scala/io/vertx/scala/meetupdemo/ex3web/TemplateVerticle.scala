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
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
class TemplateVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val port = config.getInteger(httpPort, 8083)
    val templateEngine = HandlebarsTemplateEngine.create()
    val router = Router.router(vertx)

    //load templates by automatically resolving them
    router.get("/templates/*").handler(TemplateHandler.create(templateEngine, "io/vertx/scala/meetupdemo/ex3web/templates/", "text/html"))

    //render template explicitly
    router.get("/")
      .handler(r => {
        r.put("myTestVar", "TestVar")
        templateEngine
        .renderFuture(r, "io/vertx/scala/meetupdemo/ex3web/templates/test.hbs")
        .andThen{
          case Success(f) => r.response().end(f)
          case Failure(t) => r.response().end(t.getMessage)
        }})

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(port)
      .map(_ => ())
  }
}
