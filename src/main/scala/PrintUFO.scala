import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by wangpeihu on 2017/5/30.
  */
object PrintUFO {

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("printUFO").setMaster("local"))
    val file = sc.textFile("d:\\ufo_awesome.tsv")
//    val pairs = file.map(s => (s, 1))
//    val counts = pairs.reduceByKey((a, b) => a + b)
//    print(counts)
    val first = file.count()
    print(first)
    println("xixihaha")
  }
}
