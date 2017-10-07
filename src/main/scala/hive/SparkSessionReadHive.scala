package hive

import org.apache.spark.sql.SparkSession

object SparkSessionReadHive {

  def main(args: Array[String]): Unit = {
    //    new SparkContext(new SparkConf().setAppName("appName").setMaster("local"))
    val spark = SparkSession.builder().appName("sparkSessionReadHiveTable").enableHiveSupport().getOrCreate()
    spark.sql("select * from muku.ps_yard_areas").show()
  }

}
