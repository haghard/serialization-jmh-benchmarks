scalaVersion := "2.13.1"

name := "serialization-jmh-benchmarks"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
  "io.spray" %%  "spray-json" % "1.3.5",
  "com.esotericsoftware" % "kryo" % "4.0.2", //"5.0.0-RC4"
  //"com.twitter" %%  "chill" % "0.9.3",
  "org.apache.avro" % "avro" % "1.9.1",
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "commons-codec" % "commons-codec" % "1.11",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

//lazy val protocol = RootProject(file("../protocol"))

val circeVersion = "0.12.0"

scalafmtOnCompile := true

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-jackson29"
).map(_ % circeVersion)

enablePlugins(JmhPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
