package io.vertx.scala.meetupdemo

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.Vertx
import io.vertx.scala.meetupdemo.ex3web.TemplateVerticle

/**
  * Launch a Vertx instance for playing around.
  */
object VerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[TemplateVerticle])
  }
}
