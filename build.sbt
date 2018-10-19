import sbt.Developer

name := "ok-ml-pipelines"

// Have to fix Guava in order to avoid conflict on Stopwatch in FileInputFormat (of haddop 2.6.5)
libraryDependencies += "com.google.guava" % "guava" % "16.0.1" withSources()

libraryDependencies ++= {
  val sparkVer = "2.3.1"
  Seq(
    "org.apache.spark"     %% "spark-core"              % sparkVer withSources() exclude("com.google.guava", "guava"),
    "org.apache.spark"     %% "spark-mllib"             % sparkVer withSources() exclude("com.google.guava", "guava"),
    "org.apache.spark"     %% "spark-sql"               % sparkVer withSources() exclude("com.google.guava", "guava"),
    "org.apache.spark"     %% "spark-streaming"         % sparkVer withSources() exclude("com.google.guava", "guava"),

    "com.esotericsoftware" % "kryo" % "4.0.1"
  )
}

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "org.mockito" % "mockito-core" % "2.13.0" % Test
)

libraryDependencies ++= {
  val luceneVersion = "5.4.1"
  Seq(
    "org.apache.lucene"    % "lucene-core"             % luceneVersion,
    "org.apache.lucene"    % "lucene-analyzers-common" % luceneVersion,

    "com.optimaize.languagedetector" % "language-detector"  % "0.6" exclude("com.google.guava", "guava"),
    "com.tdunning" % "t-digest" % "3.2"
  )
}

libraryDependencies ++= Seq(
  "ml.dmlc" % "xgboost4j" % "0.80",
  "ml.dmlc" % "xgboost4j-spark" % "0.80"
)


organization := "ru.odnoklassniki"

version := "0.4.0-SNAPSHOT"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.11")

licenses := Seq("Apache 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://github.com/odnoklassniki/ok-ml-pipelines"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/odnoklassniki/ok-ml-pipelines"),
    "scm:git@github.com:odnoklassniki/ok-ml-pipelines.git"
  )
)

developers := List(
  Developer(
    id    = "DmitryBugaychenko",
    name  = "Dmitry Bugaychenko",
    email = "dmitry.bugaychenko@corp.mail.ru",
    url   = url("https://www.linkedin.com/comm/in/dmitrybugaychenko")
  ),
  Developer(
    id    = "EugenyMalyutin",
    name  = "Eugeny Malyutin",
    email = "eugeny.malyutin@corp.mail.ru",
    url   = url("https://github.com/WarlockTheGrait")
  )
)

publishMavenStyle := true

pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".sonatype" / "credentials.ini")

useGpg := true
