import java.sql.{ResultSet, DriverManager}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 *
 * Created by phwang on 2017/2/13.
 */
object MySQLOper {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("DBShow").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val data = new JdbcRDD(
      sparkContext,
      connection,
      "select count(*) from gov_command where commandId >= ? and commandId <= ?",
      lowerBound = 0,
      upperBound = 11252,
      numPartitions = 1,
      mapRow = extractValues)
    println(data.collect().toList)
  }


  def connection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root")
  }

  def extractValues(result: ResultSet) = {
    result.getInt(1)

  }

}
