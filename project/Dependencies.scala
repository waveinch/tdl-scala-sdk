import sbt._

object Dependencies {

  object Version{
    val playStandalone = "1.1.3"
    val circe = "0.9.0"
  }

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"
  lazy val playWs = "com.typesafe.play" %% "play-ahc-ws-standalone" % Version.playStandalone
  lazy val circeCore = "io.circe" %% "circe-core" % Version.circe
  lazy val circeGeneric = "io.circe" %% "circe-generic" % Version.circe
  lazy val circeParser = "io.circe" %% "circe-parser" % Version.circe
}
