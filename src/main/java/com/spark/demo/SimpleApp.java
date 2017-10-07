package com.spark.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SQLContext;

public class SimpleApp {
    public static void main(String[] args) {
        System.out.println("20156======test----file");
        //        readFromMysql();
        //        readfile();

        //saveAsText(containsA, containsB);

        // parallelize
        //parallelize(sc);
    }

    private static void readFromMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String           url        = "jdbc:mysql://localhost:3306/test";
        SparkConf        conf       =
          new SparkConf().setAppName("Simple Application").setMaster("local");
        JavaSparkContext sc         = new JavaSparkContext(conf);
        SQLContext       sqlContext = new org.apache.spark.sql.SQLContext(sc);

    }

    private static Connection mysqlConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/test";
            return DriverManager.getConnection(url, "root", "root");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException |
          SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection oracleConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            String url = "jdbc:oracle:thin:@10.48.193.234:1521:hpdev26";
            return DriverManager.getConnection(url, "rcontrol", "rcontrol");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException |
          SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     */
    private static void readfile() {
        String           logFile =
          "/opt/dev/phwang/README.md"; // Should be some file on your system
        SparkConf        conf    = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc      = new JavaSparkContext(conf);

        JavaRDD<String> logData = sc.textFile(logFile).cache();

        //
        JavaRDD<String> containsA =
          logData.filter((Function<String, Boolean>) s -> s.contains("a"));
        long numAs = containsA.count();

        JavaRDD<String> containsB =
          logData.filter((Function<String, Boolean>) s -> s.contains("b"));

        long numBs = containsB.count();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }

    private static void parallelize(JavaSparkContext sc) {
        JavaRDD<Integer> parallelize = sc.parallelize(Arrays.asList(1, 2, 3, 4));
        JavaRDD<Object>  mapResult   = parallelize.map((Function<Integer, Object>) x -> x * x);
        List<Object>     collect     = mapResult.collect();
        System.out.println(StringUtils.join(collect, ","));
    }

    private static void saveAsText(JavaRDD<String> containsA, JavaRDD<String> containsB) {
        JavaRDD<String> union = containsA.union(containsB);
        union.saveAsTextFile("~/");

    }
}