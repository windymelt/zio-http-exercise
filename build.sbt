val scala3Version = "3.6.3"

lazy val root = project
  .in(file("."))
  .settings(
    name                                   := "zio-http-exercise",
    version                                := "0.1.0-SNAPSHOT",
    scalaVersion                           := scala3Version,
    fork                                   := true,
    libraryDependencies += "dev.zio"       %% "zio-http" % "3.0.1",
    libraryDependencies += "dev.zio"       %% "zio-json" % "0.7.17",
    libraryDependencies += "org.scalameta" %% "munit"    % "1.0.0" % Test,
  )
