import org.apache.spark.sql.SparkSession;

public class ReadHive {
    public static void main(String[] args) {
        SparkSession sparkSession =
          SparkSession.builder().appName("SparkReadHiveOfJava").enableHiveSupport().getOrCreate();
        sparkSession.sql("select * from muku.ps_yard_areas").show();
    }
}
