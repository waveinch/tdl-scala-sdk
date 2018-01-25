package tdl.model

case class Team(
                 _id: Option[String],
                 name: String,
                 athletesIds: List[String]
               )