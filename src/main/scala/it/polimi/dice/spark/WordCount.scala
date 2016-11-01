package it.polimi.dice.spark

import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args:Array[String]) : Unit = {
    System.setProperty("home.dir", "/Users/michele/tool/spark-2.0.1-bin-hadoop2.7/")

    val conf = new SparkConf(true).
      setMaster("local[*]").
      setAppName("Spark WordCount").
      set("spark.cassandra.connection.host", "127.0.0.1")

    val sc = new SparkContext(conf)
    
    val sentences = sc.cassandraTable[(String)]("sentencesks", "sentences").select("sentence")

    val textRDD = sc.parallelize(List(sentences.fold("")((f: String, s: String) => f + " " + s)))

    val splits = textRDD.flatMap(line => line.split(" ")).map(word =>(word,1))
    val counts = splits.reduceByKey((x,y)=>x+y)
    splits.saveAsTextFile("/Users/michele/tool/SplitOutput")
    counts.saveAsTextFile("/Users/michele/tool/CountOutput")

  }
}