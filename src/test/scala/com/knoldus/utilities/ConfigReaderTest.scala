package com.knoldus.utilities

import org.scalatest.flatspec.AnyFlatSpec
import com.knoldus.utilities.ConfigReader._
import org.scalatest.matchers.should.Matchers

class ConfigReaderTest extends AnyFlatSpec with Matchers{

  "username" should "return user name" in {
    userName should not be empty
  }

  "password" should "return user password" in {
    password should not be empty
  }

}
