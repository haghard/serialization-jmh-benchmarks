package benchmark

import org.scalatest.{FunSpec, Matchers}

class SimpleTest extends FunSpec with Matchers {

  describe("SimpleTest") {
    val answer = SprayJsonTest.decode()

    it("should parse spray") {
      SprayJsonTest.decode() should be(answer)
    }

    it("should parse with jackson") {
      JacksonTest.decode() should be(answer)
    }

    it("should parse with CirceJackson") {
      CirceJacksonSupport.decode().right.get should be(answer)
    }
  }
}
