package tdl
import tdl.client.Client
import tdl.model.{Form, FormData, Team}

import scala.concurrent.ExecutionContext

class TDLRest(client:Client)(implicit ec:ExecutionContext) extends TDL {
  import io.circe._, io.circe.generic.auto._

  private val emptyTeam = Team(Some("noteam"),"",List())

  override def teams() = client.get[Seq[Team]]("/team").map(_.toSeq.flatten)

  override def forms() = client.get[Seq[Form]]("/form").map(_.toSeq.flatten)


  override def formData(forms: Seq[Form]) = formData(None,None,forms,Seq(emptyTeam))

  override def formData(start: Long, end: Long, forms: Seq[Form], team: Seq[Team]) = formData(Some(start),Some(end),forms,team)

  override def formData(start: Option[Long], end: Option[Long], forms: Seq[Form], team: Seq[Team]) = {

    val parameters = Seq(
      start.map(s => s"start=$s"),
      end.map(s => s"end=$s"),
      forms.headOption.map(s => s"formsId=${forms.flatMap(_._id).mkString(",")}"),
      team.headOption.map(s => s"teamId=${team.flatMap(_._id).mkString(",")}")
    ).flatten.mkString("&")

    client.get[Seq[FormData]](s"/formData?$parameters")
  }.map(_.toSeq.flatten)
}
