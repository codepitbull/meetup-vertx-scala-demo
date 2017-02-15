import sbt.Package._

version := "0.1-SNAPSHOT"
name := "meetup-vertx-demo"
organization := "io.vertx"
scalaVersion := "2.12.1"

libraryDependencies ++= Vector (
  Library.vertxLangScala,
  Library.vertxCodegen,
  Library.vertxWeb,
  Library.prometheusDropwizard,
  Library.prometheusVertx,
  Library.dropwizard,
  Library.vertxHandlebars,
  Library.hazelcast,
  Library.scalaTest       % "test"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) => MergeStrategy.last
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case PathList("codegen.json") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:io.vertx.scala.meetupdemo.StarterVerticle"))
