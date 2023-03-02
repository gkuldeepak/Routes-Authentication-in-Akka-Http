package com.knoldus.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.{Authorization, BasicHttpCredentials}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.models.Models.PingResponse
import com.knoldus.services.PingService
import com.typesafe.config.ConfigFactory
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import akka.testkit.TestKit
import com.knoldus.models.Protocols._

import scala.concurrent.Future

class PingRouteTest extends AnyFlatSpec with Matchers with ScalatestRouteTest{

  override def afterAll(): Unit = {
    super.afterAll()
    TestKit.shutdownActorSystem(system, verifySystemShutdown = true)
  }

  val pingService: PingService = mock[PingService]
  val pingRoute = new PingRoute

  val conf = ConfigFactory.load()

  val userName = conf.getString("user-name")
  val password = conf.getString("user-pass")
  val validCredentials = BasicHttpCredentials(userName, password)
  val invalidCredentials = BasicHttpCredentials("", "")

  it should "execute ping service" in {
    when(pingService.ping) thenReturn Future.successful(PingResponse("Pong"))

    Get("/ping") ~> addHeader(Authorization(validCredentials)) ~> (pingRoute.route) ~> check {
      responseAs[PingResponse].message should not be empty
      status shouldBe StatusCodes.OK
    }
  }

  it should "not execute ping service" in {
    when(pingService.ping) thenReturn Future.successful(PingResponse("Invalid Credentials"))

    Get("/ping") ~> addHeader(Authorization(invalidCredentials)) ~> (pingRoute.route) ~> check {
      status shouldBe StatusCodes.Unauthorized
    }
  }
}
