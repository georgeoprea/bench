package bench.test;

import hdd.HDDReadSpeed;
import hdd.HDDWriteSpeed;

public class TestBenchHDD {
    public static void main(String[] args) {
        //HDDWriteSpeed hddTest = new HDDWriteSpeed();
        //hddTest.warmup();
        //hddTest.run("fs", true);
        HDDReadSpeed hddTest = new HDDReadSpeed();
        hddTest.run(HDDReadSpeed.ReadOptions.NIO);
        //hddTest.run(HDDReadSpeed.ReadOptions.STREAM);
    }
}
