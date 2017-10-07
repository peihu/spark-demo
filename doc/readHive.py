from os.path import expanduser, join, abspath

from pyspark.sql import SparkSession
from pyspark.sql import Row

# warehouse_location points to the default location for managed databases and tables
warehouse_location = abspath('spark-warehouse')

spark = SparkSession.builder.appName("Python Spark SQL Hive integration example").enableHiveSupport().getOrCreate()
spark.sql("select * from muku.ps_yard_areas").show()

