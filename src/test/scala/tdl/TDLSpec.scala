package tdl

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class TDLSpec extends FlatSpec with Matchers with ScalaFutures {

  val tdl = TDL("api.access","you api code here==","https://api-elle.teamdatalog.com")

  implicit val defaultPatience = PatienceConfig(timeout =  Span(10, Seconds), interval = Span(5, Millis))


  "Teams" should "be major than one" in {
    whenReady(tdl.teams()) { teams =>
      teams.nonEmpty shouldBe true
    }
  }

  "Forms" should "be major than one" in {
    whenReady(tdl.forms()) { forms =>
      forms.nonEmpty shouldBe true
    }
  }

  "FromDatas" should "be major than one" in {

    val request = for{
      teams <- tdl.teams()
      forms <- tdl.forms()
      result <- tdl.formData(1,999999999999999999L,forms,teams)
    } yield result

    whenReady(request) { teams =>
      teams.nonEmpty shouldBe true
    }
  }
}
