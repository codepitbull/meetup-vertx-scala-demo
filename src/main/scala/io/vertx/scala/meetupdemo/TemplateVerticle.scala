package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.sockjs.{BridgeOptions, PermittedOptions, SockJSHandler}
import io.vertx.scala.ext.web.handler.{StaticHandler, TemplateHandler}
import io.vertx.scala.ext.web.templ.HandlebarsTemplateEngine

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class TemplateVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val promise = Promise[Unit]

    vertx.setPeriodic(1000, a => vertx.eventBus().send("browser",s"from ${getClass.getName}"))
    vertx.eventBus().consumer("server", {a:Message[String] => println(a.body())})

    val templateEngine = HandlebarsTemplateEngine.create()

    val router = Router.router(vertx)
    router.route("/static/*").handler(StaticHandler.create())
    router.get("/test").handler(r => templateEngine.renderFuture(r, "templates/test.hbs").map(f => r.response().end(f)))
    router.route("/eventbus/*").handler(initSockJs)

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(8668)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }

  def initSockJs = {
    val sockJSHandler = SockJSHandler.create(vertx)
    val options = BridgeOptions()
      .addOutboundPermitted(PermittedOptions().setAddress("browser"))
      .addInboundPermitted(PermittedOptions().setAddress("server"))
    sockJSHandler.bridge(options)
    sockJSHandler
  }
}
