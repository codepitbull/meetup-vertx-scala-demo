package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.sockjs.{BridgeOptions, PermittedOptions, SockJSHandler}
import io.vertx.scala.ext.web.handler.{StaticHandler, TemplateHandler}
import io.vertx.scala.ext.web.templ.HandlebarsTemplateEngine

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Example for using the browser bridge from a verticle.
  */
class BridgeVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    val port = Option(config.getInteger(httpPort).intValue()).getOrElse(8080)
    val promise = Promise[Unit]

    vertx.setPeriodic(1000, a => vertx.eventBus().send("browser",s"from ${getClass.getName}"))
    vertx.eventBus().consumer("server", {a:Message[String] => println(a.body())})

    val router = Router.router(vertx)
    router.route("/static/*").handler(StaticHandler.create())
    router.route("/eventbus/*").handler(initSockJs)
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

  def initSockJs = {
    val sockJSHandler = SockJSHandler.create(vertx)
    val options = BridgeOptions()
      .addOutboundPermitted(PermittedOptions().setAddress("browser"))
      .addInboundPermitted(PermittedOptions().setAddress("server"))
    sockJSHandler.bridge(options)
    sockJSHandler
  }
}
