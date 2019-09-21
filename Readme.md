#Jmh serialization/deserialization benchmark

    sbt jmh:run -i 3 -wi 3 -f1 -t1 .*MyBenchmark.*    
    Which means 3 iterations, 3 warmup iterations, 1 fork, 1 thread.
    
    Ubuntu jdk1.8.0_131
    Benchmark                                  Mode  Cnt       Score     Error    Units
    [info] Benchmark.chill                     thrpt   10  153285.711 ± 6181.098  ops/s
    [info] Benchmark.circeJacksonDecodeEncode  thrpt   10   26348.435 ±  681.726  ops/s
    [info] Benchmark.jacksonDecode             thrpt   10   82489.306 ± 3618.685  ops/s
    [info] Benchmark.javaSerialization         thrpt   10   23735.188 ± 1803.868  ops/s
    [info] Benchmark.kryo                      thrpt   10  266956.564 ± 3826.712  ops/s