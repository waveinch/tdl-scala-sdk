package tdl.model


import io.circe.Json



case class FormData(
                     _id: Option[String],
                     formId: String,
                     submitted: Long,
                     userId: String,
                     athleteId: Option[String] = None,
                     teamId: Option[String] = None,
                     attendees: Option[Seq[String]] = None,
                     start: Option[Long] = None,
                     title: Option[String] = None,
                     end: Option[Long] = None,
                     data: Json
                   )




