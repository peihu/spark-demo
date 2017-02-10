## spark快速大数据分析-读书笔记

### 第三章：RDD编程

```
// 转化成RDD
val lines = sc.textFile("../../full-server.log")

val defualtRDD = lines.filter(line => line.contains("org.pbsframework.ebs.impl.DefaultDataBus"))
val defaultMes = lines.filter(line => line.contains("org.springframework.jms.listener.DefaultMessageListenerContainer"))
```
```
// 对RDD直接操作(Actions)
val unionAll = defualtRDD.union(defaultMes)

println("Input defualtRDD:count:" + defualtRDD.count() + "concerning lines")	
println("Here are 10 example:")
defualtRDD.take(10).foreach(println)
// save as file
lines.saveAsTextFile()
```

### 第四章：键值对操作
- 键值对RDD通常用来进行聚合计算
-  


### 第五章：数据读取与保存

#### 三种常见的数据源
- 文件格式与文件系统

    | 格式名称|结构化|
    |-|-|
    |文本文件|否|
    |JSON|半结构化|
    |CSV|是|
    |SequenceFiles|是|
    |Protocol Buffers|是|
    |对象文件|是|

- Spark SQL中的结构化数据源
- 数据库与键值存储
#### 数据库
    - java数据库连接
    
    
---
- 提交到任务中
    ```
    ./spark-submit --class "SimpleApp" --master local[4] ~/phwang/job/spark-job-1.0-SNAPSHOT.jar
    其中 local[4]的意思是：使用四个核心
    ```
    
    
### 第七章：在集群上运行Spark
    
#### 驱动器在spark中的2个职责
- 把用户程序转换成任务