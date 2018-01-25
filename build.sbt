import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ch.wavein",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
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
