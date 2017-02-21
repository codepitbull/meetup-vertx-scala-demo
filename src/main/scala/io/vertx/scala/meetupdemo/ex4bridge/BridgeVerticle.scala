package io.vertx.scala.meetupdemo.ex4bridge

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.StaticHandler
import io.vertx.scala.ext.web.handler.sockjs.{BridgeOptions, PermittedOptions, SockJSHandler}
import io.vertx.scala.meetupdemo.httpPort

import scala.concurrent.Future

/**
  * Example for using the browser bridge from a verticle.
  *
  * http://127.0.0.1:8084/static/index.html
  */
class BridgeVerticle extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {
    val port = config.getInteger(httpPort, 8084)
    vertx.setPeriodic(1000, a => vertx.eventBus().send("browser", s"from ${getClass.getName}"))
    vertx.eventBus().consumer("server", { a: Message[String] => println(a.body()) })

    val router = Router.router(vertx)
    router.route("/static/*").handler(StaticHandler.create())
    router.route("/eventbus/*").handler(initSockJs)
    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(port)
        .map(_ => ())
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
