name := "BotRoutePlanner"

version := "0.1"

scalaVersion := "2.13.8"
val scalaTestV = "3.2.11"
val akkaV = "2.6.19"
val akkaHttpV = "10.2.9"

libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestV % Test
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"
libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.17.5" % Test
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaV % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % akkaV % Test
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % Test
