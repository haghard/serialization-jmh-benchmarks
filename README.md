#Jmh serialization/deserialization benchmark

```bash
sbt jmh:run -i 3 -wi 3 -f1 -t1 .*MyBenchmark.*
``` 

Which means 3 iterations, 3 warmup iterations, 1 fork, 1 thread.


# Results 

```bash 

[info] MyBenchmark.avro   thrpt    3  285775.880 ±  67034.846  ops/s
[info] MyBenchmark.kryo   thrpt    3  331666.432 ±  90422.461  ops/s
[info] MyBenchmark.proto  thrpt    3  336822.287 ± 147134.326  ops/s

or

[info] MyBenchmark.avro    avgt    3       3.708 ±     0.921  us/op
[info] MyBenchmark.kryo    avgt    3       2.946 ±     2.223  us/op
[info] MyBenchmark.proto   avgt    3       2.859 ±     0.584  us/op

```   
    
Latency:

avro  takes 3.7 microsec per serialization/deserialization

kryo  takes 2.9 microsec per serialization/deserialization

proto takes 2.8 microsec per serialization/deserialization



## Jmh Links
http://tutorials.jenkov.com/java-performance/jmh.html
https://lansalo.com/2018/08/22/microbenchmarking-and-performance-tests-in-scala-jmh-and-scalameter/   


## Apache avro
https://avro.apache.org/docs/current/spec.html#schema_record
https://www.baeldung.com/java-apache-avro
http://bigdatums.net/2016/01/20/simple-apache-avro-example-using-java/
https://github.com/apache/avro/tree/master/doc/examples/java-example/src/main/java/example
https://liyanxu.blog/2018/02/07/apache-avro-examples/  
https://avro.apache.org/docs/current/gettingstartedjava.html
https://stackoverflow.com/questions/28808479/how-to-generate-schema-less-avro-files-using-apache-avro
https://github.com/Banno/salat-avro