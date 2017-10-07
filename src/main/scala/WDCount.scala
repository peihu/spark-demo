import org.apache.spark.{SparkConf, SparkContext}

object WDCount {
  def main(args: Array[String]): Unit = {
    //    val master_url    = "spark://192.168.1.10:7077"
    val hb_master_url = "spark://192.168.249.131:7077"
    val sc = new SparkContext(new SparkConf().setAppName("WDCount").setMaster(hb_master_url))
    //    val file = sc.textFile("derby.log")
    //    val wdcount = file.count()

    println("hello wc count")
  }
}
