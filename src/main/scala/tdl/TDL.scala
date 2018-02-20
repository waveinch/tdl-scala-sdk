package tdl

import tdl.client.PlayClient
import tdl.model.{Form, FormData, Team}

import scala.concurrent.{ExecutionContext, Future}


trait TDL{
  def teams():Future[Seq[Team]]
  def forms():Future[Seq[Form]]
  def formData(forms:Seq[Form]):Future[Seq[FormData]]
  def formData(start:Long,end:Long,forms:Seq[Form],team:Seq[Team]):Future[Seq[FormData]]
  def formData(start:Option[Long],end:Option[Long],forms:Seq[Form],team:Seq[Team]):Future[Seq[FormData]]


}

object TDL {
  def apply(username:String,authCode:String,endpoint:String)(implicit ec:ExecutionContext): TDL = {
    val client = new PlayClient(username,authCode,endpoint)
    new TDLRest(client)
  }
}
