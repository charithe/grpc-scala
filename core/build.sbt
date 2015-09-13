addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)

libraryDependencies ++= Seq(
    "com.github.charithe" % "grpc-samples" % "1.0.0-SNAPSHOT",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
)


//scalacOptions += "-Xshow-phases"
