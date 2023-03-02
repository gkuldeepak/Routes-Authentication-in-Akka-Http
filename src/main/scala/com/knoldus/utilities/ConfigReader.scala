package com.knoldus.utilities

import com.typesafe.config.{Config, ConfigFactory}

object ConfigReader {

  private val conf: Config = ConfigFactory.load()

  val userName = conf.getString("user-name")
  val password = conf.getString("user-pass")

}
