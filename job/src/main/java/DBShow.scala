import java.sql.{DriverManager, ResultSet}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * scala版本的数据读取
 * Created by phwang on 2017/2/10.
 */
object DBShow {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("DBShow").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)



    val data = new JdbcRDD(
      sparkContext,
      mysqlConnection,
      "select count(*) from gov_command where commandId >= ? and commandId <= ?",
      lowerBound = 0,
      upperBound = 11252,
      numPartitions = 1,
      mapRow = extractValues)



    println(data.collect().toList)
  }


  def mysqlConnection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root")
  }

  def extractValues(result: ResultSet) = {
    result.getInt(1)
    /*

        (
          result.getInt(1)
          , result.getString(2)
          , result.getString(3)
          , result.getString(4)
          , result.getString(5)

          )
    */

  }

}
