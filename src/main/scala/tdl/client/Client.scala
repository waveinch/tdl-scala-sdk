package tdl.client


import io.circe.Decoder

import scala.concurrent.Future

trait Client {
  def get[T](path:String)(implicit d:Decoder[T]):Future[Option[T]]
}
