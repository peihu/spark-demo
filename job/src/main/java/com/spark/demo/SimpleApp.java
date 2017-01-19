package com.spark.demo;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.List;

public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "/opt/dev/phwang/README.md"; // Should be some file on your system
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
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        // ´æÎÄ¼þ
        //saveAsText(containsA, containsB);

        // parallelize
        //parallelize(sc);
    }

    private static void parallelize(JavaSparkContext sc) {
        JavaRDD<Integer> parallelize = sc.parallelize(Arrays.asList(1, 2, 3, 4));
        JavaRDD<Object> mapResult = parallelize.map(new Function<Integer, Object>() {
            public Object call(Integer x) throws Exception {
                return x * x;
            }
        });
        List<Object> collect = mapResult.collect();
        System.out.println(StringUtils.join(collect, ","));
    }

    private static void saveAsText(JavaRDD<String> containsA, JavaRDD<String> containsB) {
        JavaRDD<String> union = containsA.union(containsB);
        union.saveAsTextFile("~/");

    }
}