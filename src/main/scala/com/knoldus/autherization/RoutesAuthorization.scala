package com.knoldus.autherization

import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directive0
import akka.http.scaladsl.server.Directives.{complete, extractCredentials, pass}
import com.knoldus.utilities.ConfigReader.{password, userName}

trait RoutesAuthorization {

  def authenticate: Directive0 = {
    extractCredentials.flatMap {
      case Some(credentials: BasicHttpCredentials) =>
        println(Console.YELLOW + s"[INFO] Credentials extracted user-name ${credentials.username} and user-pass ${credentials.password} for authentication" + Console.RESET)
        proceedAuthentication(credentials.username, credentials.password)

      case _ =>
        println(Console.RED + "No Credentials Found" + Console.RESET)
        rejectRequest
    }
  }

  private def proceedAuthentication(uName: String, uPass: String): Directive0 = {
    if(userName == uName && uPass == password) {
      println(Console.GREEN + "Authentication Successful" + Console.RESET)
      pass
    } else {
      println(Console.RED + "Invalid Credentials" + Console.RESET)
      rejectRequest
    }
  }

  private def rejectRequest: Directive0 = {
    complete(HttpResponse(StatusCodes.Unauthorized, entity = HttpEntity(ContentTypes.`application/json`, "Invalid Credentials")))
  }


}
