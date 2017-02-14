import java.sql.{DriverManager, ResultSet}
import java.util.Properties

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * scala版本的数据读取
 * Created by phwang on 2017/2/10.
 */
object DBShow {

  def main(args: Array[String]) {

    val sparkContext = new SparkContext(new SparkConf().setAppName("DBShow").setMaster("local"))

    val sqlContext: SQLContext = new SQLContext(sparkContext)

    val prop = new Properties()
    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance
    prop.put("user", "rcontrol")
    prop.put("password", "rcontrol")
    val df: DataFrame = sqlContext.read.jdbc(
      "jdbc:oracle:thin:@10.48.193.234:1521:hpdev26", "gov_para_system", prop)

    // 所有的结果集，转成list
    println(df.collect().toList)

    //
    println("=============>>  ")
    println("df.printSchema()  ")
    df.printSchema()


    println("=============>>  ")
    println("df.select(\"GOVSYSTEMNAME\")")
    println(df.select("GOVSYSTEMNAME"))


    println("=============>>  ")
    println(df.count())

    println(df.rdd.partitions.size)
    //executeQuery(sparkContext)
    df.show()

  }

  def executeInsert(sparkContext: SparkContext): Unit = {

  }

  def executeQuery(sparkContext: SparkContext): Unit = {
    // lowerBounder 是第一个占位符
    // upperBounder 是第二个占位符
    // numPartitions 是结果分为几部分
    val data = new JdbcRDD(
      sparkContext, conn,
      "select * from gov_para_system where rownum >= ? and rownum <=?",
      1, 6, 2,
      extractValues)
    println(data.collect().toSet)
  }


  def conn() = {
    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance
    val url = "jdbc:oracle:thin:@10.48.193.234:1521:hpdev26"
    DriverManager.getConnection(url, "rcontrol", "rcontrol")
  }

  def extractValues(result: ResultSet) = {
    (result.getString(1), result.getString(2))
  }

}
