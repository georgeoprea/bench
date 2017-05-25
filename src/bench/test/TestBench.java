package bench.test;

import CPU.*;
import CPU.CPURecursionVsLooping.Looping;
import bench.*;
import bench.cpu.CPURecursionLoopUnrolling;
import logging.*;
import timing.*;
import hdd.*;
import hdd.HDDReadSpeed.ReadOptions;

public class TestBench {

    public static void main(String[] args) {
        /*
         * // Triple For int size = 500;
		 * 
		 * CPUBenchmark bench = new CPUBenchmark(); ConsoleLog log = new
		 * ConsoleLog(); Timer timer = new Timer(); TimeUnit timeUnit =
		 * TimeUnit.Nano;
		 * 
		 * bench.initialize(size); timer.start(); bench.run();
		 * log.writeTime("Time: ", timer.stop(), timeUnit);
		 * 
		 * // Binary Search int size2 = 500; CPUBenchmark bench2 = new
		 * CPUBenchmark(); ConsoleLog log2 = new ConsoleLog(); Timer timer2 =
		 * new Timer(); TimeUnit timeUnit2 = TimeUnit.Nano;
		 * 
		 * bench2.initialize(size2); timer2.start(); bench2.run();
		 * log2.writeTime("Time: ", timer2.stop(), timeUnit2);
		 * 
		 * // Digits of Pi int size3 = 10; CPUPi bench3 = new CPUPi();
		 * ConsoleLog log3 = new ConsoleLog(); Timer timer3 = new Timer();
		 * TimeUnit timeUnit3 = TimeUnit.Nano;
		 * 
		 * bench3.initialize(size3); timer2.start(); bench3.run();
		 * log3.writeTime("Time: ", timer3.stop(), timeUnit3);
		 */

		/*
         * // Array int size4 = 10000; CPUArray bench4 = new CPUArray();
		 * ConsoleLog log4 = new ConsoleLog(); Timer timer4 = new Timer();
		 * TimeUnit timeUnit4 = TimeUnit.Nano; //bench4.initialize(1000);
		 * 
		 * bench4.initialize(size4); bench4.warmUp(1);
		 * 
		 * //for(int i = 0; i <20; i++){
		 * 
		 * timer4.start(); bench4.run(); long t = timer4.stop();
		 * log4.writeTime("Time: ", t, timeUnit4); //}
		 */

		/*
		 * // Fixed Point
		 * 
		 * CPUFixedPoint bench5 = new CPUFixedPoint(); ConsoleLog log5 = new
		 * ConsoleLog(); Timer timer5 = new Timer(); TimeUnit timeUnit5 =
		 * TimeUnit.Nano;
		 * 
		 * int size = 1000000; bench5.initialize(size);
		 * bench5.warmUp(Branches.doBranching); timer5.start();
		 * bench5.run(Branches.doBranching); long time5 = timer5.stop();
		 * log5.writeTime("\nTime: ", time5, timeUnit5); float nrOfGflops =
		 * 8f*size/time5; System.out.println(nrOfGflops + " MOPS"); /*
		 * 
		 * /* CPURecursionVsLooping bench6 = new CPURecursionVsLooping();
		 * ConsoleLog log6 = new ConsoleLog(); Timer timer6 = new Timer();
		 * TimeUnit timeUnit6 = TimeUnit.Nano;
		 * 
		 * bench6.initialize(600); timer6.start();
		 * bench6.run(Looping.RECURSIVE); long time6 = timer6.stop();
		 * log6.writeTime("\n", time6, timeUnit6);
		 * 
		 * bench6.initialize(600); timer6.start();
		 * bench6.run(Looping.ITERATIVE); long timer7 = timer6.stop();
		 * log6.writeTime("\n", timer7, timeUnit6);
		 */


        CPURecursionLoopUnrolling bench7 = new CPURecursionLoopUnrolling();
        ConsoleLog log7 = new ConsoleLog();
        Timer timer7 = new Timer();
        TimeUnit timeUnit7 = TimeUnit.Nano;

        bench7.initialize(100);
        timer7.start();
        bench7.run(true, 500);
        long
                time7 = timer7.stop();
        log7.writeTime("\n", time7, timeUnit7);

		/*
		 * CPUThread bench8 = new CPUThread(); ConsoleLog log8 = new
		 * ConsoleLog(); Timer timer8 = new Timer();
		 * 
		 * float score = 0.0f; double time = 0; int nrT = 0; int size =
		 * 10000000; bench8.initialize(size); for(int nrThreads = 1 ; nrThreads
		 * <= 16 ; nrThreads *= 2){ timer8.start(); bench8.run(new
		 * Integer(nrThreads)); long time8 = timer8.stop() / 1000000; // Nano ->
		 * Milli System.out.println("Nr of threads: " + nrThreads + "\nTime: " +
		 * time8 + " " + "ms\n");
		 * 
		 * time += time8; nrT += nrThreads; } score = (float) (size/(time*nrT));
		 * System.out.print(score);
		 */
		/*
		 * 
		 * CPUThRoots tb = new CPUThRoots(); ILog log = new ConsoleLog(); int
		 * size = 10_000_000; tb.initialize(size); log.write(tb.getScore());
		 */
		/*
		 * CPUThreadedHashing bench9 = new CPUThreadedHashing(); ConsoleLog log9
		 * = new ConsoleLog(); Timer timer9 = new Timer(); TimeUnit tu9 =
		 * TimeUnit.Nano;
		 */
        // 399595309 MILLI
        // 372834671 Sec

        // HDDWriteSpeed bench = new HDDWriteSpeed();
        // bench.run("fs", true);

        HDDReadSpeed hddTest = new HDDReadSpeed();
        hddTest.run(HDDReadSpeed.ReadOptions.NIO);

    }
}
