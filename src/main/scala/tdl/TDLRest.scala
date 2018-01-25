package tdl
import tdl.client.Client
import tdl.model.{Form, FormData, Team}

import scala.concurrent.ExecutionContext

class TDLRest(client:Client)(implicit ec:ExecutionContext) extends TDL {
  import io.circe._, io.circe.generic.auto._

  override def teams() = client.get[Seq[Team]]("/team").map(_.toSeq.flatten)

  override def forms() = client.get[Seq[Form]]("/form").map(_.toSeq.flatten)

  override def formData(start: Long, end: Long, forms: Seq[Form], team: Seq[Team]) = {
    client.get[Seq[FormData]](s"/formData?end=$end&start=$start&formsId=${forms.flatMap(_._id).mkString(",")}&teamId=${team.flatMap(_._id).mkString(",")}")
  }.map(_.toSeq.flatten)
}
