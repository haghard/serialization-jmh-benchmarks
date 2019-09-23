#Jmh serialization/deserialization benchmark

```bash
sbt jmh:run -i 3 -wi 3 -f1 -t1 .*MyBenchmark.*
```   
    
Which means 3 iterations, 3 warmup iterations, 1 fork, 1 thread.   