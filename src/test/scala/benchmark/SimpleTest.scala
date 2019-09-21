package benchmark

import org.scalatest.{FunSpec, Matchers}

class SimpleTest extends FunSpec with Matchers {

  describe("SimpleTest") {
    val answer = SprayJsonTest.decode()

    it("should parse using Spray") {
      SprayJsonTest.decode() should be(answer)
    }

    it("should parse using Jackson") {
      JacksonTest.decode() should be(answer)
    }

    it("should parse using CirceJackson") {
      CirceJacksonSupport.decode().right.get should be(answer)
    }
  }
}
