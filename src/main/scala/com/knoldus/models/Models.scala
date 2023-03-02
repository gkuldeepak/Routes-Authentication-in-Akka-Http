package com.knoldus.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.models.Models.PingResponse
import spray.json.DefaultJsonProtocol

object Models {

  final case class PingResponse(message: String)

}

object Protocols extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val PingResponseFormat = jsonFormat1(PingResponse)
}
