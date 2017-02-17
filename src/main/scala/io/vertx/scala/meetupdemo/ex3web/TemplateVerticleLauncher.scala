package io.vertx.scala.meetupdemo.ex3web

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.Vertx

/**
  * Launch a Vertx instance for playing around.
  */
object TemplateVerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[TemplateVerticle])
  }
}