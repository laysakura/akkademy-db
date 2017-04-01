lazy val commonSettings = Seq(
  organization := "com.github.laysakura",
  scalaVersion := "2.12.1",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
  version := (version in ThisBuild).value,
  parallelExecution in ThisBuild := false
)

lazy val versions = new {
  val logback = "1.1.7"

  val akka = "2.4.17"
  val scalatest = "3.0.0"
  val specs2 = "2.4.17"
}

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  aggregate(`akkademy-db`)

lazy val common = (project in file("common")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % versions.logback,

      "org.scalatest" %% "scalatest" % versions.scalatest % "test",
      "org.specs2" %% "specs2-mock" % versions.specs2 % "test"
    )
  )

lazy val `akkademy-db` = (project in file("akkademy-db")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-slf4j" % versions.akka,
      "com.typesafe.akka" %% "akka-actor" % versions.akka,
      "com.typesafe.akka" %% "akka-testkit" % versions.akka % "test"
    )
  ).
  dependsOn(common % "test->test;compile->compile")
