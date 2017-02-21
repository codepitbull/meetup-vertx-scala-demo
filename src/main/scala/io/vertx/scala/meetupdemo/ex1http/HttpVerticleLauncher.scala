package io.vertx.scala.meetupdemo.ex1http

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.Vertx
import io.vertx.scala.meetupdemo.ex3web.TemplateVerticle

/**
  * Launch a Vertx instance and deploy HttpVerticle
  *
  * @author <a href="mailto:jochen.mader@codecentric.de">Jochen Mader</a>
  */
object HttpVerticleLauncher {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[HttpVerticle])
  }
}
