package hive

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}


object ReadHiveWithHiveContext {

  def main(args: Array[String]): Unit = {
    /**
      * 比较老的方式，1.x使用这种方式
      * 2.x使用SparkSession的方式
      */
    val sc = new SparkContext(new SparkConf().setAppName("readhive").setMaster("local"))
    val hiveContext = new HiveContext(sc)
    val stud_infoRDD = hiveContext.sql("select * from muku.ps_yard_areas")
    stud_infoRDD.take(20).foreach(line => println("code:" + line(0) + ";name:" + line(1)))
  }
}

