package com.github.sampleapi.controllers

import cats.{Applicative, Monad}
import io.netty.handler.codec.http.HttpMessage
import unfiltered.response.{JsonContent, Ok, ResponseFunction, ResponseString}

object HelloController {

  def apply[M[_]: Applicative](name: String): M[ResponseFunction[HttpMessage]] =
    Applicative[M].pure(JsonContent ~> Ok ~> ResponseString(s"Hello $name"))

}
