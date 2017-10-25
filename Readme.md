#jmh serialization benchmarks

    runMain benchmark.runner.Runner -i 5 -wi 5 -f 2 -t 1

    [info] DecodeBenchmark.jacksonDecodeEncode          thrpt   10   84755.290 ± 7267.595  ops/s
    [info] DecodeBenchmark.kryoFromObject2BytesAndBack  thrpt   10  282205.884 ± 8059.902  ops/s
