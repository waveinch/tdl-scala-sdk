package tdl.model



import io.circe.Json


case class OptionField(id: String, name: String)

case class Table(
                  id:String,
                  name:String,
                  columns: List[String],
                  rows: List[String]
                )

case class UI(
               tables: Seq[Table]
             )

case class TableField(
                       id: String,
                       columnPosition: Int,
                       rowPosition: Int
                     )

case class ExternalField(
                          formId:String,
                          order:Option[String],
                          filter:Option[Json],
                          limit:Option[Int]
                        )

case class Field(
                  name: String,
                  description: Option[String],
                  id: String,
                  typ: String,
                  warningOverlap: Boolean = false, // unico per campo
                  required: Boolean = false,
                  labelCols: Option[Int] = None,
                  cols: Option[Int] = None,
                  options: Option[List[OptionField]],
                  table: Option[TableField],
                  external: Option[ExternalField] = None
                )

case class FormDraftDefinition(
                                id: String,
                                value: String
                              )

case class FormProperties(
                           team: Boolean = false,
                           athlete: Boolean = false,
                           date: Boolean = false,
                           timespan: Boolean = false,
                           datespan: Boolean = false,
                           withAttendees: Boolean = false,
                           showInCalendar: Boolean = false,
                           showInReport: Boolean = false,
                           showInForm: Boolean = false,
                           customClass: Option[String] = None,
                           color: Option[String] = None, // #cccccc notation
                           textColor: Option[String] = None,
                           showOnlyBusy: Boolean = false,
                           overlaps:OverlapsProperties,
                           dateField:Option[String] = None,
                           labels: List[String], // lista di field id che compongono la label del form
                           draft: Option[FormDraftDefinition],
                           `public`:Option[Boolean] = None,
                           notification: Option[NotificationProperties],
                           moves: Option[MovesProperties]
                         )

case class OverlapsProperties(
                               warningGlobal: Boolean = false, // unico degli unici
                               warning: Boolean = false,
                               forceNoOverlap: Boolean = false

                             )

case class NotificationProperties(
                                   owner:NotificationVector,
                                   write:NotificationVector,
                                   read:NotificationVector
                                 ) {
  def enabled = owner.enabled || write.enabled || read.enabled
}

case class NotificationVector(email:Boolean) {
  def enabled = email
}

case class MovesProperties(
                            copyToTeam:Boolean,
                            moveToTeam:Boolean,
                            changeToForm:Seq[String], // lista di form id con il quale si vuole abilitare il cambio di tipo form
                            copyToForm:Seq[String], // lista di form id con il quale si vuole abilitare il cambio di tipo form
                            actions:Seq[FormAction]
                          )

case class FormAction(name:String,copy:Boolean,mask:Json)


case class CalculatedFields(name:String, expression:String)

case class AliasFields(name:String,target:String)

case class Form(
                 _id: Option[String],
                 name: String,
                 properties: FormProperties,
                 description: Option[String],
                 fields: List[Field],
                 calculatedFields: List[CalculatedFields],
                 aliases: List[AliasFields],
                 order: Double,
                 ui: Option[UI],
                 userEnabled: List[String],
                 teamEnabled: List[String],
                 allEnabled: Boolean
               )


