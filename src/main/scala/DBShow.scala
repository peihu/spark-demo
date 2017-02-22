import java.sql.{ResultSet, DriverManager}
import java.util.Properties

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SaveMode, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * scala版本的数据读取
 * Created by phwang on 2017/2/10.
 */
object DBShow {

  def main(args: Array[String]) {

    val sparkContext = new SparkContext(new SparkConf().setAppName("DBShow").setMaster("local"))

    //mysqlSave(sparkContext)

    oracleSave(sparkContext)
  }


  def mysqlSave(sparkContext: SparkContext): Unit = {
    val sqlContext: SQLContext = new SQLContext(sparkContext)

    val prop = new Properties()
    Class.forName("com.mysql.jdbc.Driver").newInstance
    prop.put("user", "root")
    prop.put("password", "root")

    case class User(GOVSYSTEMNAME: String, CONNECTIONPARAMETER: String)

    val users = sparkContext.parallelize(1 to 10).map(f => User("k-" + f, "val-" + f)).map(f => Row(f.GOVSYSTEMNAME, f.CONNECTIONPARAMETER))

    println("===================")
    println("=users.foreach(println)=")
    users.foreach(println)
    println("===================")

    val schema = StructType(Array(StructField("k_a", StringType, true), StructField("v_a", StringType, true)))


    sqlContext.createDataFrame(users, schema).write.mode(SaveMode.Append).jdbc(
      "jdbc:mysql://localhost:3306/test", "st", prop)


  }


  def oracleSave(sparkContext: SparkContext): Unit = {
    val sqlContext: SQLContext = new SQLContext(sparkContext)
    val prop = new Properties()
    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance
    val url = "jdbc:oracle:thin:@10.48.193.234:1521/hpdev26"
    prop.put("user", "rcontrol")
    prop.put("password", "rcontrol")

    case class User(GOVSYSTEMNAME: String, CONNECTIONPARAMETER: String)

    val users = sparkContext.parallelize(1 to 10).map(f => User("key-" + f, "val-" + f)).map(f => Row(f.GOVSYSTEMNAME, f.CONNECTIONPARAMETER))

    val schema = StructType(Array(StructField("GOVSYSTEMNAME", StringType, true), StructField("CONNECTIONPARAMETER", StringType, true)))


    // use jdbcUtils.saveTable
    org.apache.spark.sql.execution.datasources.jdbc.JdbcUtils.saveTable(sqlContext.createDataFrame(users, schema), url, "GOV_PARA_SYSTEM", prop)


    // user dataFrame.write.mode
    //    sqlContext.createDataFrame(users, schema).write.mode(SaveMode.Append).jdbc(url, "GOV_PARA_SYSTEM", prop)

  }

  def dbShow(sqlContext: SQLContext): Unit = {
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
    println(df.select("GOVSYSTEMNAME", "CONNECTIONPARAMETER").show())

    println("=============>>  ")
    println(df.count())
    println(df.rdd.partitions.size)
    //executeQuery(sparkContext)

    //df.show()
    df.write.mode(SaveMode.Append).saveAsTable("gov_para_system")

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
