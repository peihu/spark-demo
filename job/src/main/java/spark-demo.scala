//
//val lines = sc.textFile("../../full-server.log")
//
//val defualtRDD = lines.filter(line => line.contains("org.pbsframework.ebs.impl.DefaultDataBus"))
//val defaultMes = lines.filter(line => line.contains("org.springframework.jms.listener.DefaultMessageListenerContainer"))
//
//val unionAll = defualtRDD.union(defaultMes)
//
//println("Input defualtRDD:count:" + defualtRDD.count() + "concerning lines")
//println("Here are 10 example:")
//defualtRDD.take(10).foreach(println)
//// save as file
//defualtRDD.saveAsTextFile()
//
//
//// invoke by parameter as function
//
//class SearchFunctions(){
//
//
//}
//
//##


//val jdbcDF = sqlContext.read.format("jdbc").options(Map("url" -> "jdbc:mysql://localhost:3306/test", "driver" -> "com.mysql.jdbc.Driver", "dbtable" -> "user", "user" -> "root", "password" -> "root")).load()