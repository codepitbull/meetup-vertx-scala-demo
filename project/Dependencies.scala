import sbt._

object Version {
  final val Scala       = "2.12.1"
  final val ScalaTest   = "3.0.1"
  final val Vertx       = "3.4.0-SNAPSHOT"
  final val Prometheus  = "0.0.19"
}

object Library {
  val vertxCodegen          = "io.vertx"       %  "vertx-codegen"                    % Version.Vertx     % "provided" changing()
  val vertxLangScala        = "io.vertx"       %% "vertx-lang-scala"                 % Version.Vertx                  changing()
  val vertxWeb              = "io.vertx"       %% "vertx-web-scala"                  % Version.Vertx                  changing()
  val vertxHandlebars       = "io.vertx"       %% "vertx-web-templ-handlebars-scala" % Version.Vertx                  changing()
  val dropwizard            = "io.vertx"       %% "vertx-dropwizard-metrics-scala"   % Version.Vertx                  changing()
  val hazelcast             = "io.vertx"       %  "vertx-hazelcast"                  % Version.Vertx                  changing()
  val prometheusVertx       = "io.prometheus"  %  "simpleclient_vertx"               % Version.Prometheus
  val prometheusDropwizard  = "io.prometheus"  %  "simpleclient_dropwizard"          % Version.Prometheus
  val scalaTest             = "org.scalatest"  %% "scalatest"                        % Version.ScalaTest              changing()
}