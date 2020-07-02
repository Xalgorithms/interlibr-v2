// Copyright (C) 2020 Don Kelly <karfai@gmail.com>

// This file is part of Interlibr, a functional component of an
// Internet of Rules (IoR).

// ACKNOWLEDGEMENTS
// Funds: Xalgorithms Foundation
// Collaborators: Don Kelly, Joseph Potvin and Bill Olders.

// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU Affero General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Affero General Public License for more details.

// You should have received a copy of the GNU Affero General Public
// License along with this program. If not, see
// <http://www.gnu.org/licenses/>.
lazy val VERSION_SCALA             = "2.13.3"
lazy val VERSION_SCALA_TEST        = "3.2.0"
lazy val VERSION_SCALA_MOCK        = "4.4.0"
lazy val VERSION_STTP              = "2.0.0"
lazy val VERSION_SCALA_ISO         = "0.1.2"
lazy val VERSION_JODA              = "2.10.6"
lazy val VERSION_JODA_CONVERT      = "2.2.1"

lazy val meta = Seq(
  name := """service-il-index""",
  organization := "org.xalgorithms",
  version := "0.0.1",
  scalaVersion := VERSION_SCALA,
)

lazy val lib_deps = Seq(
// NO 2.13
//  guice,
//  "com.softwaremill.sttp"  %% "core"                    % VERSION_STTP,
//  "com.softwaremill.sttp"  %% "akka-http-backend"       % VERSION_STTP,
//  "com.vitorsvieira"       %% "scala-iso"               % VERSION_SCALA_ISO,
  "joda-time"              %  "joda-time"               % VERSION_JODA,
  "org.joda"               %  "joda-convert"            % VERSION_JODA_CONVERT,
// optional: https://www.scalatest.org/user_guide/using_scalatest_with_sbt
//  "org.scalatic"           %% "scalactic"               % VERSION_SCALA_TEST,
  "org.scalatest"          %% "scalatest"               % VERSION_SCALA_TEST % "test",
  "org.scalamock"          %% "scalamock"               % VERSION_SCALA_MOCK % Test
)

lazy val root = (project in file("."))
  .settings(meta)
  .settings(libraryDependencies ++= lib_deps)
// NO 2.13
//  .enablePlugins(PlayScala)
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)
  .enablePlugins(AshScriptPlugin)

dockerBaseImage := "openjdk:jre-alpine"
dockerUsername := Some("xalgorithms")

// this makes sure we don't generate /opt/docker/RUNNING_PID
javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.xalgorithms.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.xalgorithms.binders._"
