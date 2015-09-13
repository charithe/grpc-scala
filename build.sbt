addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)

lazy val commonSettings = Seq(
  organization := "com.github.charithe",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  autoCompilerPlugins := true,
  scalacOptions += "-language:experimental.macros",
  resolvers ++= Seq(Resolver.mavenLocal, Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots"))
)

lazy val macros = (project in file("macros")).settings(commonSettings: _*)

lazy val core = (project in file("core")).settings(commonSettings: _*).dependsOn(macros)







