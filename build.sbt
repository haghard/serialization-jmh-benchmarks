scalaVersion := "2.12.4"

name := "serialization-jmh-benchmarks"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.1",
  "io.spray" %%  "spray-json" % "1.3.3",
  "com.esotericsoftware" % "kryo" % "4.0.1",
  "com.twitter" %%  "chill" % "0.9.2",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-jackson28"
).map(_ % circeVersion)

enablePlugins(JmhPlugin)
