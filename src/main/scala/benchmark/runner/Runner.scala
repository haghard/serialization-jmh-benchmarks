package benchmark.runner

import java.io.{File, FileOutputStream}
import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.options.CommandLineOptions
import scala.collection.JavaConverters._

/*
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home

runMain benchmark.runner.Runner -i 5 -wi 5 -f 2 -t 1

[info] DecodeBenchmark.jacksonDecodeEncode          thrpt   10   84755.290 ± 7267.595  ops/s
[info] DecodeBenchmark.kryoFromObject2BytesAndBack  thrpt   10  282205.884 ± 8059.902  ops/s

 */

object Runner {
  val enc = "UTF-8"

  def main(args: Array[String]): Unit = {
    val opts    = new CommandLineOptions(args: _*)        // parse command line arguments, and then bend them to your will! ;-)
    val runner  = new org.openjdk.jmh.runner.Runner(opts) // full access to all JMH features, you can also provide a custom output Format here
    val results = runner.run                              // actually run the benchmarks

    val f = new FileOutputStream(new File("benchmark.out"))
    collectionAsScalaIterable(results).foreach { result: RunResult ⇒
      // usually you'd use these results to report into some external aggregation tool for example
      val params = result.getParams
      f.write(
        s"""*********************Begin************************
        |${params.getBenchmark}
        |Result: ${result.getAggregatedResult.getPrimaryResult}
        |*********************End**************************
      """.stripMargin.getBytes(enc)
      )
    }
    f.close()
  }
}
