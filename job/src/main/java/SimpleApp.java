import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.List;

public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "YOUR_SPARK_HOME/README.md"; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> logData = sc.textFile(logFile).cache();


        //







        JavaRDD<String> containsA = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("a");
            }
        });
        long numAs = containsA.count();

        JavaRDD<String> containsB = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("b");
            }
        });
        long numBs = containsB.count();

        JavaRDD<String> union = containsA.union(containsB);

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        //////////////////

        JavaRDD<Integer> parallelize = sc.parallelize(Arrays.asList(1, 2, 3, 4));
        JavaRDD<Object> mapResult = parallelize.map(new Function<Integer, Object>() {
            public Object call(Integer x) throws Exception {
                return x * x;
            }
        });
        List<Object> collect = mapResult.collect();


        union.saveAsTextFile("~/");
        System.out.println(StringUtils.join(collect, ","));
    }
}