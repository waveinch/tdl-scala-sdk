package tdl.client

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.ws.DefaultBodyReadables._
import play.api.libs.ws.DefaultBodyWritables._
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}


class PlayClient(username:String,authCode:String, endpoint:String)(implicit ec:ExecutionContext) extends Client {

  import io.circe._, io.circe.parser._, io.circe.syntax._

  implicit val system = ActorSystem()
  system.registerOnTermination {
    System.exit(0)
  }
  implicit val materializer = ActorMaterializer()

  // Create the standalone WS client
  // no argument defaults to a AhcWSClientConfig created from
  // "AhcWSClientConfigFactory.forConfig(ConfigFactory.load, this.getClass.getClassLoader)"
  val wsClient = StandaloneAhcWSClient()

  override def get[T](path: String)(implicit d:Decoder[T]) = {
    wsClient.url(s"$endpoint$path")
      .withHttpHeaders(("Auth-Password",authCode),("Auth-Username",username))
      .get().map{ response =>
        val body = response.body
        val json = parse(body)
        val decoded = json.right.toOption.map(_.as[T])
        decoded.flatMap(_.right.toOption)
      }
  }
}
