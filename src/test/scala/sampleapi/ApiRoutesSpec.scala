package sampleapi

import com.github.sampleapi.routes.ApiRoutes
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification
import unfiltered.response.ResponseString

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class ApiRoutesSpec extends Specification with ScalaCheck {

  "routes" should {

    "delegate to Controller" in {

      forAll(Gen.alphaStr) { (name: String) =>
        name.nonEmpty ==> {

          val mockController = (_: String) => Future.successful(ResponseString(s"Hello $name"))
          val apiRoutes = new ApiRoutes(mockController)
          val eventualResponseFunction = apiRoutes.routes(MockHttpRequest(uri = s"/hello/$name"))
          val r = Await.result(eventualResponseFunction, Duration("3s"))
          UnfilteredResponse.body(r) ==== s"Hello $name"

        }
      }

    }

  }

}
