package sampleapi

import cats.Id
import com.github.sampleapi.controllers.HelloController
import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

class HelloControllerSpec extends Specification with ScalaCheck {

  "apply" should {

    "return Hello `name` given a `name`" in {

      forAll { (name: String) =>

        val controllerResponse = HelloController.apply[Id](name)

        UnfilteredResponse.body(controllerResponse) ==== s"Hello $name"

      }

    }

  }

}
