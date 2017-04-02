lazy val commonSettings = Seq(
  organization := "com.github.laysakura",
  scalaVersion := "2.12.1",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint", "-language:postfixOps"),
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
  aggregate(
    `common`,
    `akkademy-core`,
    `akkademy-server`
  )

lazy val common = (project in file("common")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % versions.logback,

      "com.typesafe.akka" %% "akka-slf4j" % versions.akka,
      "com.typesafe.akka" %% "akka-actor" % versions.akka,
      "com.typesafe.akka" %% "akka-testkit" % versions.akka % "test",

      "org.scalatest" %% "scalatest" % versions.scalatest % "test",
      "org.specs2" %% "specs2-mock" % versions.specs2 % "test"
    )
  )

lazy val `akkademy-core` = (project in file("akkademy-core")).
  settings(commonSettings: _*).
  settings(
    name := "akkademy-core",
    libraryDependencies ++= Seq(
    )
  ).
  dependsOn(common % "test->test;compile->compile")

lazy val `akkademy-server` = (project in file("akkademy-server")).
  settings(commonSettings: _*).
  settings(
    name := "akkademy-server",
    libraryDependencies ++= Seq(
    )
  ).
  dependsOn(`akkademy-core` % "test->test;compile->compile")
