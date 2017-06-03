package com.github.sampleapi

import unfiltered.netty.future._

import scala.concurrent.ExecutionContext

class NettyServer(port: Int, routes: Plan.Intent)(implicit executionContext: ExecutionContext) extends UnsafeLogger {
  val server = unfiltered.netty.Server.http(port).plan(Planify(routes))

  def start() = {
    server.start()
  }

  def stop() = {
    server.stop()
  }
}
