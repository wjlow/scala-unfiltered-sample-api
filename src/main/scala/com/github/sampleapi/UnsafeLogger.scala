package com.github.sampleapi

import org.slf4j.LoggerFactory

trait UnsafeLogger {
  val log = LoggerFactory.getLogger(getClass)
}
