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