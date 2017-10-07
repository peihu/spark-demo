import org.apache.spark.{SparkConf, SparkContext}

/**
  * 打印UFO 出现次数
  * Created by wangpeihu on 2017/5/30.
  */
object PrintUFO {

  def main(args: Array[String]): Unit = {
    val url = "spark://192.168.1.10:7077"
    val name = "PrintUFO of h1"
    val conf = new SparkConf().setAppName(name).setMaster("local")
    val sc = new SparkContext(conf)

    val file = sc.textFile("d:\\ufo_awesome.tsv")
    //    val pairs = file.map(s => (s, 1))
    //    val counts = pairs.reduceByKey((a, b) => a + b)
    //    print(counts)
    val first = file.count()
    print(first)
    println("xixihaha")
  }
}

// ./spark-submit --master local[2] --class "PrintUFO" spark-job-1.0-SNAPSHOT.jar
// ./spark-submit --master local[2] --driver-class-path /opt/users/ojdbc14.jar --class "PrintUFO" spark-job-1.0-SNAPSHOT.jar