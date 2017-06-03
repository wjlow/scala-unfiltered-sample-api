package sampleapi

import java.nio.charset.Charset

import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.{http => netty}
import unfiltered.netty.ResponseBinding
import unfiltered.response.{HttpResponse, ResponseFunction}

object UnfilteredResponse {

  def body(r: ResponseFunction[netty.HttpResponse]): String = getBody(run(r))
  def statusCode(r: ResponseFunction[netty.HttpResponse]): Int = getStatusCode(run(r))
  def header(name: String)(r: ResponseFunction[netty.HttpResponse]): String = getHeader(name)(run(r))

  private def run(f: ResponseFunction[netty.HttpResponse]): HttpResponse[DefaultFullHttpResponse] =
    f(new ResponseBinding(new netty.DefaultFullHttpResponse(netty.HttpVersion.HTTP_1_1, netty.HttpResponseStatus.OK)))

  private def getBody(resp: HttpResponse[netty.DefaultFullHttpResponse]) = resp.underlying.content().toString(Charset.defaultCharset())
  private def getStatusCode(resp: HttpResponse[netty.DefaultFullHttpResponse]) = resp.underlying.status.code
  private def getHeader(name: String)(r: HttpResponse[netty.DefaultFullHttpResponse]) = r.underlying.headers.get(name)

}