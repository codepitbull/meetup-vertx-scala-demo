package io.vertx.scala.meetupdemo.ex5metrics

import com.codahale.metrics.SharedMetricRegistries
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.dropwizard.DropwizardExports
import io.prometheus.client.vertx.MetricsHandler
import io.vertx.ext.web.{Router => JRouter, RoutingContext => JRoutingContext}
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.scala.meetupdemo.httpPort

import scala.concurrent.Future

/**
  * Example for integrating Prometheus with Vert.x.
  * To actuall get some metrics you will have to specify the system property that names the
  * metrics registry -Dvertx.metrics.options.registryName=exported
  *
  */
class MetricsVerticle extends ScalaVerticle {
  override def startFuture(): Future[Unit] = {
    val port = config.getInteger(httpPort, 8080)
    CollectorRegistry.defaultRegistry.register(new DropwizardExports(SharedMetricRegistries.getOrCreate("exported")))

    val router = Router.router(vertx)
    val prometheus = new MetricsHandler()
    router.route("/metrics").handler(ctx => prometheus.handle(ctx.asJava.asInstanceOf[JRoutingContext]))
    router.asJava.asInstanceOf[JRouter].route("/metrics").handler(new MetricsHandler())

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(port)
        .map(_ => ())
  }
}
