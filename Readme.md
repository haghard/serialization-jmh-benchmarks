#jmh serialization benchmarks

    runMain benchmark.runner.Runner -i 5 -wi 5 -f 2 -t 1

    MacBook Pro - jdk1.8.0_121
    [info] Benchmark           Mode  Cnt       Score       Error  Units
    [info] Benchmark.circe    thrpt   10   23486.815 ±  1286.155  ops/s
    [info] Benchmark.jackson  thrpt   10   72081.163 ±  7393.306  ops/s
    [info] Benchmark.kryo     thrpt   10  236361.521 ± 17001.818  ops/s
    
    Size: 694 vs 1015 bite
    
