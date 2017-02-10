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
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class SimpleApp {
    public static void main(String[] args) {
        readFromMysql();
        //        readfile();

        //saveAsText(containsA, containsB);

        // parallelize
        //parallelize(sc);
    }

    private static void readFromMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/test";
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new org.apache.spark.sql.SQLContext(sc);

    }

    private static Connection mysqlConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/test";
            return DriverManager.getConnection(url, "root", "root");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection oracleConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            String url = "jdbc:oracle:thin:@10.48.193.234:1521:hpdev26";
            return DriverManager.getConnection(url, "rcontrol", "rcontrol");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     */
    private static void readfile() {
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