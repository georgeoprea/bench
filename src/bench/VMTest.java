package bench;

import hdd.RAM.MemoryMapper;

/**
 * Created by georgeoprea on 16/05/17.
 */
public class VMTest {
    public static void main(String[] args) {
        VirtualMemoryBenchmark bench = new VirtualMemoryBenchmark();
        long fileSize = 1024 * 1024 * 1024 * 10L;
        int bufferSize = 1024 * 4;
        bench.run(fileSize, bufferSize);
        System.out.println(bench.getResult());
    }
}
