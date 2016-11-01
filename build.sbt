lazy val root = (project in file(".")).
  settings(
    name := "spark-cassandra-exmaple",
    version := "1.0",
    scalaVersion in ThisBuild := "2.11.8",
    mainClass in Compile := Some("it.polimi.dice.spark.WordCount.scala")        
  )

resolvers ++= Seq(
  "All Spark Repository -> bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"
)

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.0.1" % "provided",
  "org.apache.spark" % "spark-sql_2.11" % "2.0.1" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.0-M3"
)

// META-INF discarding
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}
