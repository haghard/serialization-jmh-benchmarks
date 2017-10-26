#jmh serialization benchmarks

    runMain benchmark.runner.Runner -i 5 -wi 5 -f 2 -t 1

    MacBook Pro 2014 - jdk1.8.0_121
    [info] Benchmark           Mode  Cnt       Score       Error  Units
    [info] Benchmark.circe    thrpt   10   23486.815 ±  1286.155  ops/s
    [info] Benchmark.jackson  thrpt   10   72081.163 ±  7393.306  ops/s
    [info] Benchmark.kryo     thrpt   10  236361.521 ± 17001.818  ops/s

    Ubuntu jdk1.8.0_131
    [info] Benchmark           Mode  Cnt       Score       Error  Units
    [info] Benchmark.circe    thrpt   10   27314.924 ±  1105.621  ops/s
    [info] Benchmark.jackson  thrpt   10   87960.705 ±  3459.806  ops/s
    [info] Benchmark.kryo     thrpt   10  267269.353 ± 13795.028  ops/s

    Size: 694 vs 1015 bytes
    
