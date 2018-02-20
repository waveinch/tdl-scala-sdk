import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ch.wavein",
      scalaVersion := "2.11.11",
      version      := "0.2.0-SNAPSHOT"
    )),
    name := "tdl-sdk",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      playWs,
      circeCore,
      circeGeneric,
      circeParser
    )
  )
