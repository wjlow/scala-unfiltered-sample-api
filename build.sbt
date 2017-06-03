val appName = "scala-unfiltered-api"

name := appName

version := "0.1"

scalaVersion := "2.12.1"

sbtVersion := "0.13.11"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

libraryDependencies ++= Seq(
  "ws.unfiltered" %% "unfiltered-netty-server" % "0.9.1",
  "ws.unfiltered" %% "unfiltered-filter" % "0.9.1",
  "org.apache.httpcomponents" % "httpcore" % "4.4.5",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test"
)

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

val specs2Version = "3.8.9"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core",
  "org.specs2" %% "specs2-mock",
  "org.specs2" %% "specs2-scalacheck"
).map(_ % specs2Version % "test")

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-Ypartial-unification",
  "-unchecked",
  "-feature",
  "-language:higherKinds")