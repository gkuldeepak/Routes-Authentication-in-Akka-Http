package com.knoldus.bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.concat
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.routes.PingRoute
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object Application extends App{

  implicit val actorSystem = ActorSystem("Authorization_System")
  implicit val materializer = ActorMaterializer

  val pingRoute = new PingRoute
  val route: Route = {
    concat(
      pingRoute.route
    )
  }

  Http().bindAndHandle(route, "localhost", 9000).onComplete{
    case Success(value) =>
      println(Console.GREEN +
        """
          |Knoldus Inc
          | Server Up and Running
          |""".stripMargin + Console.RESET)

    case Failure(exception) =>
      println(Console.RED +
        s"""
           |Knoldus Inc
           | Server Failed with ${exception}
           |""".stripMargin + Console.RESET)
  }


}
