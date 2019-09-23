#Jmh serialization/deserialization benchmark

```bash
sbt jmh:run -i 3 -wi 3 -f1 -t1 .*MyBenchmark.*
```   
    
Which means 3 iterations, 3 warmup iterations, 1 fork, 1 thread.


## Links
http://tutorials.jenkov.com/java-performance/jmh.html
https://lansalo.com/2018/08/22/microbenchmarking-and-performance-tests-in-scala-jmh-and-scalameter/   