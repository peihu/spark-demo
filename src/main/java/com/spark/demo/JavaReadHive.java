package com.spark.demo;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaReadHive {

    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    private static final String connectionUrl    = "jdbc:hive2://h4.hadoop.com:2181,h1.hadoop.com:2181,h2.hadoop"
      + ".com:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";

    public static void main(String[] args) throws IOException {

        Connection con = null;

        String sql = "SELECT count(*) from muku.tdabc_qc_time_funcation";
        try {
            // Set JDBC Hive Driver
            Class.forName(JDBC_DRIVER_NAME);
            // Connect to Hive
            con = DriverManager.getConnection(connectionUrl, "", "");
            // Init Statement
            Statement stmt = con.createStatement();
            System.out.println("execute: " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(
                  rs.getString(1));
            }
            System.out.println("Select from Hive table : OK");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}