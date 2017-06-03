package com.github.sampleapi.routes

import com.github.sampleapi.UnsafeLogger
import io.netty.handler.codec.http.HttpMessage
import unfiltered.netty.future.Plan
import unfiltered.request.{GET, Path, Seg}
import unfiltered.response.ResponseFunction

import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions

class ApiRoutes(helloController: String => Future[ResponseFunction[HttpMessage]])
               (implicit executionContext: ExecutionContext) extends UnsafeLogger {

  def routes: Plan.Intent = {
    case GET(Path(Seg("hello" :: name :: Nil))) => helloController(name)
  }

}