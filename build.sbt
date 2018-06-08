organization := "me.shadaj"

name := "scalapy"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.6"

sourceGenerators in Compile += Def.task {
  val fileToWrite = (sourceManaged in Compile).value / "ObjectTupleReaders.scala"
  val methods = (2 to 22).map { n =>
    s"""implicit def tuple${n}Reader[${(1 to n).map(t => s"T$t").mkString(", ")}](implicit ${(1 to n).map(t => s"r$t: ObjectReader[T$t]").mkString(", ")}): ObjectReader[(${(1 to n).map(t => s"T$t").mkString(", ")})] = {
       |  new ObjectReader[(${(1 to n).map(t => s"T$t").mkString(", ")})] {
       |    override def read(or: Any)(implicit jep: Jep) = {
       |      val orArr = or.asInstanceOf[Array[_]]
       |      (${(1 to n).map(t => s"r$t.read(orArr(${t - 1}))").mkString(", ")})
       |    }
       |  }
       |}"""
  }

  val toWrite =
    s"""package me.shadaj.scalapy.py
       |import jep.Jep
       |trait ObjectTupleReaders {
       |${methods.mkString("\n")}
       |}""".stripMargin

  IO.write(fileToWrite, toWrite)
  Seq(fileToWrite)
}

sourceGenerators in Compile += Def.task  {
  val fileToWrite = (sourceManaged in Compile).value / "ObjectTupleWriters.scala"
  val methods = (2 to 22).map { n =>
    s"""implicit def tuple${n}Writer[${(1 to n).map(t => s"T$t").mkString(", ")}](implicit ${(1 to n).map(t => s"r$t: ObjectWriter[T$t]").mkString(", ")}): ObjectWriter[(${(1 to n).map(t => s"T$t").mkString(", ")})] = {
       |  new ObjectWriter[(${(1 to n).map(t => s"T$t").mkString(", ")})] {
       |    override def write(v: (${(1 to n).map(t => s"T$t").mkString(", ")}))(implicit jep: Jep): Any = {
       |      Array[Any](${(1 to n).map(t => s"r$t.write(v._" + t + ")").mkString(",")})
       |    }
       |  }
       |}"""
  }

  val toWrite =
    s"""package me.shadaj.scalapy.py
       |import jep.Jep
       |trait ObjectTupleWriters {
       |${methods.mkString("\n")}
       |}""".stripMargin

  IO.write(fileToWrite, toWrite)
  Seq(fileToWrite)
}

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
libraryDependencies += "black.ninia" % "jep" % "3.7.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.3" % Test

fork in Test := true

javaOptions in Test += "-Djava.library.path=/usr/local/lib/python3.6/site-packages/jep"
