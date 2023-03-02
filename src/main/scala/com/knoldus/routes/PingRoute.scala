package com.knoldus.routes

import akka.http.scaladsl.server.Route
import com.knoldus.autherization.RoutesAuthorization
import akka.http.scaladsl.server.Directives._
import com.knoldus.services.PingService
import com.knoldus.models.Protocols._

class PingRoute extends RoutesAuthorization{

  val pingService = new PingService

  val route: Route = {
    authenticate {
      path("ping") {
        get {
          onSuccess(pingService.ping) { response =>
            complete(response)
          }
        }
      }
    }
  }

}
