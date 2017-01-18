## spark快速大数据分析-读书笔记

### 第三章：RDD编程

```
val lines = sc.textFile("../../full-server.log")

val defualtRDD = lines.filter(line => line.contains("org.pbsframework.ebs.impl.DefaultDataBus"))
val defaultMes = lines.filter(line => line.contains("org.springframework.jms.listener.DefaultMessageListenerContainer"))

val unionAll = defualtRDD.union(defaultMes)

println("Input defualtRDD:count:" + defualtRDD.count() + "concerning lines")	
println("Here are 10 example:")
defualtRDD.take(10).foreach(println)
// save as file
defualtRDD.saveAsTextFile()


// invoke by parameter as function

class SearchFunctions(){


}
```

### 键值对操作

- 4.1