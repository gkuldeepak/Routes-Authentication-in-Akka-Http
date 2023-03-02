package com.knoldus.services

import com.knoldus.models.Models.PingResponse

import scala.concurrent.Future

class PingService {

  def ping: Future[PingResponse] = {
    println(Console.CYAN + "Ping Service !" + Console.RESET)
    Future.successful(PingResponse("PONG"))
  }
}
