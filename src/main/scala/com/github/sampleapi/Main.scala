package com.github.sampleapi

import java.util.concurrent.Executors
import cats.instances.all._
import com.github.sampleapi.controllers.HelloController
import com.github.sampleapi.routes.{ApiRoutes, RouteNotFound}

import scala.concurrent.{ExecutionContext, Future}

object Main {
  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def main(args: Array[String]): Unit = {
    val server = nettyAppServer(routes)
    server.start()
  }

  def routes: ApiRoutes = {
    val helloController = HelloController.apply[Future] _
    new ApiRoutes(helloController)
  }

  def nettyAppServer(appRoutes: ApiRoutes)(implicit executionContext: ExecutionContext) =
    new NettyServer(8080, appRoutes.routes orElse RouteNotFound.apply)

}