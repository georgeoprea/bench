package bench;

import java.io.IOException;
import java.util.Random;

import hdd.RAM.MemoryMapper;
import timing.Timer;
import bench.IBenchmark;

public class VirtualMemoryBenchmark implements IBenchmark {

    private String result = "";

    @Override
    public void initialize(int size) {
    }

    public void warmUp() {
    }

    @Override
    public void warmUp(Object option) {

    }

    @Override
    public void warmUp(int option) {

    }

    @Override
    public void run() {
    }

    @Override
    public void run(Object... options) {
        // expected example: {fileSize, bufferSize}
        Object[] params = (Object[]) options;
        long fileSize = Long.parseLong(params[0].toString());
        int bufferSize = Integer.parseInt(params[1].toString());

        try {
            MemoryMapper core = new MemoryMapper("/Users/georgeoprea/Desktop/_core", fileSize);
            byte[] buffer = new byte[bufferSize];
            Random rand = new Random();

            Timer timer = new Timer();

            // write to VM
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                // generate random content
                // write to memory mapper
                rand.nextBytes(buffer);

                core.put(i, buffer);
            }
            long time = timer.stop() / 1000;
            long speed = 0L; /* fileSize/time */
            speed = fileSize / time;

            result = "\nWrote " + (fileSize / 1024 / 1024L)
                    + " MB to virtual memory at " + speed + " MB/s";

            // read from VM
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                // get from memory mapper
                core.get(i, bufferSize);
            }
            time = timer.stop() / 1000;
            speed = 0L; /* MB/s */
            speed = fileSize / time;

            result += "\nRead " + (fileSize / 1024 / 1024L)
                    + " MB from virtual memory at " + speed + " MB/s";

            core.purge();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return result;
    }

}
