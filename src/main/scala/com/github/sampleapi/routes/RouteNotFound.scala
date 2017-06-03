package com.github.sampleapi.routes

import unfiltered.netty.future.Plan
import unfiltered.response.{JsonContent, NotFound, ResponseString}

import scala.concurrent.{ExecutionContext, Future}

object RouteNotFound {
  def apply(implicit ec: ExecutionContext): Plan.Intent = {
    case route => Future.successful(JsonContent ~> NotFound ~> ResponseString(s"${route.uri} is not a valid route"))
  }
}