package sampleapi

import java.io.ByteArrayInputStream

import io.netty.handler.codec.{http => netty}
import unfiltered.Async
import unfiltered.netty.ReceivedMessage
import unfiltered.request.HttpRequest
import unfiltered.response.ResponseFunction


case class MockHttpRequest(method: String = "GET",
                           body: String = "",
                           protocol: String = "http",
                           uri: String = "",
                           headers: Map[String, String] = Map.empty,
                           params: Map[String, String] = Map.empty) extends HttpRequest[ReceivedMessage](null) with Async.Responder[netty.HttpResponse] {


  def inputStream: java.io.InputStream = new ByteArrayInputStream(body.getBytes)

  def reader: java.io.Reader = new java.io.InputStreamReader(inputStream)

  def parameterNames: Iterator[String] = params.keysIterator

  def parameterValues(param: String): Seq[String] = params.get(param).toSeq

  def headerNames: Iterator[String] = headers.keysIterator

  def headers(name: String): Iterator[String] = headers.get(name).iterator

  def isSecure: Boolean = false

  def remoteAddr: String = ""

  def respond(rf: ResponseFunction[netty.HttpResponse]): Unit = ()
}
