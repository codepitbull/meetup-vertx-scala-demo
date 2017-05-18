import sbt.Package._
import sbt._
import Docker.autoImport.exposedPorts

version := "0.1-SNAPSHOT"
name := "meetup-vertx-demo"
organization := "io.vertx"
scalaVersion := "2.12.1"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers +=
  "Bintray Vert.x Streams" at "http://dl.bintray.com/codepitbull/maven"

enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)

libraryDependencies ++= Vector (
  Library.vertx_lang_scala,
  Library.vertx_codegen,
  Library.vertx_web,
  Library.vertx_mqtt_server,
  Library.prometheusDropwizard,
  Library.prometheusVertx,
  Library.vertx_hazelcast,
  Library.vertx_dropwizard_metrics,
  Library.vertx_web_templ_handlebars,
  Library.logging,
  Library.lof4j2,
  Library.restAssured     % "test",
  Library.pahoMqtt        % "test",
  Library.scalaTest       % "test",

  "de.codepitbull.scala.vertx" %% "vertx-streams" % "3.4.2-P1"
)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:io.vertx.scala.meetupdemo.StarterVerticle"))