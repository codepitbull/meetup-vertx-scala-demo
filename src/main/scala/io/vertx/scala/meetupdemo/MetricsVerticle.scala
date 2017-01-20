package io.vertx.scala.meetupdemo

import com.codahale.metrics.SharedMetricRegistries
import io.prometheus.client.dropwizard.DropwizardExports
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.vertx.MetricsHandler
import io.vertx.ext.web.{Router => JRouter}
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.ext.web.{RoutingContext => JRoutingContext}

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class MetricsVerticle extends ScalaVerticle {
  override def start(): Future[Unit] = {
    val promise = Promise[Unit]
    CollectorRegistry.defaultRegistry.register(new DropwizardExports(SharedMetricRegistries.getOrCreate("exported")))

    val router = Router.router(vertx)
    val prometheus = new MetricsHandler()
    router.route("/metrics").handler(ctx => prometheus.handle(ctx.asJava.asInstanceOf[JRoutingContext]))
    router.asJava.asInstanceOf[JRouter].route("/metrics").handler(new MetricsHandler())

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(8667)
      .andThen{
        case Success(_) => promise.success(())
        case Failure(t) => promise.failure(t)
      }
    promise.future
  }
}
