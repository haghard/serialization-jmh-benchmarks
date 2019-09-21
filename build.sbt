scalaVersion := "2.12.10"

name := "serialization-jmh-benchmarks"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.1",
  "io.spray" %%  "spray-json" % "1.3.5",
  "com.esotericsoftware" % "kryo" % "4.0.2", //"5.0.0-RC4"
  "com.twitter" %%  "chill" % "0.9.2",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

val circeVersion = "0.8.0"

scalafmtOnCompile := true

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-jackson28"
).map(_ % circeVersion)

enablePlugins(JmhPlugin)
