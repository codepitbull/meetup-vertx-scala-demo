package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.Message
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.sockjs.{BridgeOptions, PermittedOptions, SockJSHandler}
import io.vertx.scala.ext.web.handler.{StaticHandler, TemplateHandler}
import io.vertx.scala.ext.web.templ.HandlebarsTemplateEngine

import scala.concurrent.Promise
import scala.util.{Failure, Success}

class TemplateVerticle extends ScalaVerticle {


  override def start(startPromise: Promise[Unit]): Unit = {
    val router = Router.router(vertx)
    val templateEngine = HandlebarsTemplateEngine.create()
    val handler = TemplateHandler.create(templateEngine)

    vertx.setPeriodic(1000, a => vertx.eventBus().send("browser","hahahaha"))

    vertx.eventBus().consumer("server", {a:Message[String] => println(a.body())})

    // This will route all GET requests starting with /templates/ to the template handler
    // E.g. /templates/graph.hbs will look for a template in /templates/templates/graph.hbs
    router.route("/static/*").handler(StaticHandler.create())
    router.get("/dynamic/*").handler(handler)
    router.get("/test").handler(r => templateEngine.renderFuture(r, "templates/test.hbs").map(f => r.response().end(f)))

    val sockJSHandler = SockJSHandler.create(vertx)
    val options = BridgeOptions()
      .addOutboundPermitted(PermittedOptions().setAddress("browser"))
      .addInboundPermitted(PermittedOptions().setAddress("server"))
    sockJSHandler.bridge(options)

    router.route("/eventbus/*").handler(sockJSHandler)

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(8668)
      .andThen{
        case Success(_) => startPromise.success(())
        case Failure(t) => startPromise.failure(t)
      }

  }
}
