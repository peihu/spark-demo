package hive

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object SparkHive {

  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setAppName("readhive").setMaster("local"))
    val hiveContext = new HiveContext(sc)
    val stud_infoRDD = hiveContext.sql("select * from muku.ps_yard_areas")
    stud_infoRDD.take(20).foreach(line => println("code:" + line(0) + ";name:" + line(1)))
  }
}

