## spark���ٴ����ݷ���-����ʼ�

### �����£�RDD���

```
// ת����RDD
val lines = sc.textFile("../../full-server.log")

val defualtRDD = lines.filter(line => line.contains("org.pbsframework.ebs.impl.DefaultDataBus"))
val defaultMes = lines.filter(line => line.contains("org.springframework.jms.listener.DefaultMessageListenerContainer"))
```
```
// ��RDDֱ�Ӳ���(Actions)
val unionAll = defualtRDD.union(defaultMes)

println("Input defualtRDD:count:" + defualtRDD.count() + "concerning lines")	
println("Here are 10 example:")
defualtRDD.take(10).foreach(println)
// save as file
lines.saveAsTextFile()
```

### �����£���ֵ�Բ���
- ��ֵ��RDDͨ���������оۺϼ���
-  


### �����£����ݶ�ȡ�뱣��

#### ���ֳ���������Դ
- �ļ���ʽ���ļ�ϵͳ

    | ��ʽ����|�ṹ��|
    |-|-|
    |�ı��ļ�|��|
    |JSON|��ṹ��|
    |CSV|��|
    |SequenceFiles|��|
    |Protocol Buffers|��|
    |�����ļ�|��|

- Spark SQL�еĽṹ������Դ
- ���ݿ����ֵ�洢
#### ���ݿ�
    - java���ݿ�����
    
    
---
- �ύ��������
    ```
    ./spark-submit --class "SimpleApp" --master local[4] ~/phwang/job/spark-job-1.0-SNAPSHOT.jar
    ���� local[4]����˼�ǣ�ʹ���ĸ�����
    ```
    
    
### �����£��ڼ�Ⱥ������Spark
    
#### ��������spark�е�2��ְ��
- ���û�����ת��������

----
#### ʹ��sparkSQL��ȡ���ݿ�
- ��ȡ

```
    // load jdbc driver
    Class.forName("com.mysql.jdbc.Driver").newInstance
    val prop = new Properties()
    prop.put("user","test")
    prop.put("password","test")
    
    // create DataFrame
    val df: DataFrame = sqlContext.read.jdbc("jdbc:mysql://localhsot:3306", "user", prop)
    
    // show schema
    df.schema()
    
    // show data table row count
    df.count()
    
    // print table content
    df.show()
```

- д���������ݿ�

```
    // load jdbc driver
    Class.forName("com.mysql.jdbc.Driver").newInstance
    val url = "jdbc:mysql://localhost:3306/test"
    val prop = new Properties()
    prop.put("user","test")
    prop.put("password","test")
         
    // init data
    case class user :(id: String, name :String)
    val users = sparkContext.parallelize(1 to 10).map(i => User("id_" + i, "name_" + i)).map(i => Row(i.id, i.name))
    
    // create schema
    val schema = StructType(Array(StructFiled("id", StringType, true),StructFiled("name", StringType, true)))

    // create dataFrame
    val df = sqlContext.createDataFrame(users, schema)
    
    // write data for mysql
    df.write.mode(SaveMode.Append).jdbc(url, "user", prop)
    
    // when the database is oracle. use follow code
    org.apache.spark.sql.execution.datasources.jdbc.JdbcUtils.saveTable(df, url, "user", prop)
```