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
lazy val VERSION_STTP              = "2.2.1"
lazy val VERSION_SCALA_ISO         = "0.1.2"
lazy val VERSION_JODA              = "2.10.6"
lazy val VERSION_JODA_CONVERT      = "2.2.1"
lazy val VERSION_TEST_PLAY         = "5.0.0"
lazy val VERSION_AKKA              = "2.6.6"
lazy val VERSION_AKKA_HTTP         = "10.1.12"
lazy val VERSION_AKKA_STREAM       = "2.5.28"

lazy val meta = Seq(
  name := "service-il-index",
  organization := "org.xalgorithms",
  version := "0.0.1",
  scalaVersion := VERSION_SCALA,
)

lazy val lib_deps = Seq(
  // Akka HTTP
  "com.typesafe.akka" %% "akka-http"                % VERSION_AKKA_HTTP,
  "com.typesafe.akka" %% "akka-http-spray-json"     % VERSION_AKKA_HTTP,
  "com.typesafe.akka" %% "akka-actor-typed"         % VERSION_AKKA,
  "com.typesafe.akka" %% "akka-stream"              % VERSION_AKKA,
  "ch.qos.logback"    % "logback-classic"           % "1.2.3",
  //  https://sttp.softwaremill.com/en/latest/quickstart.html
  "com.softwaremill.sttp.client"  %% "core"         % VERSION_STTP,
// https://sttp.softwaremill.com/en/latest/backends/akka.html
//  "com.softwaremill.sttp.client"  %% "akka-http-backend" % VERSION_STTP,
//  "com.vitorsvieira"       %% "scala-iso"               % VERSION_SCALA_ISO,
  "joda-time"              %  "joda-time"           % VERSION_JODA,
  "org.joda"               %  "joda-convert"        % VERSION_JODA_CONVERT,
// basic scala test frameworks
  "org.scalatest"          %% "scalatest"           % VERSION_SCALA_TEST % "test",
  "org.scalamock"          %% "scalamock"           % VERSION_SCALA_MOCK % Test,
  "org.scalatestplus.play" %% "scalatestplus-play"  % VERSION_TEST_PLAY % Test,
// optional: https://www.scalatest.org/user_guide/using_scalatest_with_sbt
//  "org.scalatic"           %% "scalactic"               % VERSION_SCALA_TEST,
// akka additions to testing frameworks
  "com.typesafe.akka" %% "akka-http-testkit"        % VERSION_AKKA_HTTP % Test,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % VERSION_AKKA     % Test
)

lazy val root = (project in file("."))
  .settings(meta)
  .settings(libraryDependencies ++= lib_deps)
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
