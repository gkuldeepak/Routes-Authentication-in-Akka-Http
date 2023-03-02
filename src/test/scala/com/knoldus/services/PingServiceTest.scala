package com.knoldus.services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.ExecutionContext.Implicits.global

class PingServiceTest extends AnyFlatSpec with Matchers{

  val pingService = new PingService

  "ping service" should
    "return response" in {
      val response = pingService.ping
      response.map { response =>
        response.message should not be empty
      }
    }


}
