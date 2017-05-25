package hdd;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import timing.Timer;
import bench.IBenchmark;

public class HDDRandomAccess implements IBenchmark {

    private final static String PATH = "/Users/georgeoprea/Desktop/The.Hateful.Eight.2015.BluRay.720p.DTS.x264-EPiC.mkv";
    private String result;

    @Override
    public void initialize(int fileSizeGB) {
        RandomAccessFile file;

        // Create a temporary file with random content to be used for
        // reading/writing
        try {
            file = new RandomAccessFile(PATH, "rw");
            Random rand = new Random();
            int bufferSize = 64 * 1024;
            long GB = 1024 * 1024 * 1024;
            long toWrite = (GB * fileSizeGB) / bufferSize;
            byte[] buffer = new byte[bufferSize];
            int counter = 0;

            while (counter++ < toWrite) {
                rand.nextBytes(buffer);
                file.write(buffer);
            }

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void warmup() {
        // have a Mountain Dew or Red Bull
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object[]) instead");
    }

    @Override
    public void run(Object... option) {
        // ex. {"r", "fs", 4*1024}
        Object[] param = (Object[]) option;

        try {
            // read benchmark
            if (String.valueOf(param[0]).toLowerCase().equals("r")) {
                // buffer size given as parameter
                int bufferSize = Integer.parseInt(String.valueOf(param[2]));
                // read a fixed size and measure time
                if (String.valueOf(param[1]).toLowerCase().equals("fs")) {
                    int steps = 10000;

                    long time = new RandomAccess().randomReadFixedSize(PATH, bufferSize, steps);
                    long totalMBRead = steps * bufferSize / 1024 / 1024;
                    result = steps + " random reads in " + time + " ms [" + totalMBRead + " MB] "
                            + "with speed " + totalMBRead * 1000 / time + " MB/s";
                }
                // read a fixed time amount and measure time
                else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {
                    int time = 1000;

                    int ios = new RandomAccess().randomReadFixedTime(PATH, bufferSize, time);
                    result = ios + " I/Os per second [" + (ios * bufferSize / 1024 / 1024) + " MB]";
                } else
                    throw new UnsupportedOperationException("Read option \"" + String.valueOf(param[1]) + "\" is not implemented");

            }
            // write benchmark
            else if (String.valueOf(param[0]).toLowerCase().equals("w")) {
                int bufferSize = Integer.parseInt(String.valueOf(param[1]));
                int steps = 10000;

                long time = new RandomAccess().randomWriteFixedSize(PATH, bufferSize, steps);
                long totalMBRead = steps * bufferSize / 1024 / 1024;
                result = steps + " random writes in " + time + " ms [" + totalMBRead + " MB] "
                        + "with speed " + totalMBRead / (time / 1000) + " MB/s";
            } else
                throw new UnsupportedOperationException("Benchmark option \"" + String.valueOf(param[0]) + "\" is not implemented");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void warmUp(Object option) {

    }

    @Override
    public void warmUp(int option) {

    }

    @Override
    public void clean() {
        (new File(PATH)).delete();
    }

    public String getResult() {
        return String.valueOf(result);
    }

    public void stop() {
        // TODO Auto-generated method stub

    }

    class RandomAccess {
        private Random random;

        RandomAccess() {
            random = new Random();
        }

        public long randomWriteFixedSize(String filePath, int bufferSize, int toRead) throws IOException {
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");     // file to read from
            long fileSize = (long) (file.getChannel().size() % Long.MAX_VALUE);  // size of file
            int counter = 0;            // counter for number of reads
            byte[] bytes = new byte[bufferSize];     // buffer for reading
            Timer timer = new Timer();
            Random rand = new Random();
            timer.start();
            while (counter++ < toRead) {
                long random = ThreadLocalRandom.current().nextLong(fileSize - bufferSize);
                file.seek(random); // go to random spot in file
                file.write(bytes); // read the bytes into buffer
                counter++;
            }
            file.close();
            return timer.stop() / 1000000;
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile
         *
         * @param filePath   Full path to file on disk
         * @param bufferSize Size of byte buffer to read at each step
         * @param toRead     Number of steps to repeat random read
         * @return Amount of time needed to finish given reads
         * @throws IOException
         */

        public long randomReadFixedSize(String filePath, int bufferSize, int toRead) throws IOException {
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");     // file to read from
            long fileSize = (long) (file.getChannel().size() % Long.MAX_VALUE);  // size of file
            int counter = 0;            // counter for number of reads
            byte[] bytes = new byte[bufferSize];     // buffer for reading
            Timer timer = new Timer();
            Random rand = new Random();
            timer.start();
            while (counter++ < toRead) {
                long random = ThreadLocalRandom.current().nextLong(fileSize);
                file.seek(random); // go to random spot in file
                file.read(bytes); // read the bytes into buffer
                counter++;
            }
            file.close();
            return timer.stop() / 1000000;
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile for one second, or any other given
         * time
         *
         * @param filePath   Full path to file on disk
         * @param bufferSize Size of byte buffer to read at each step
         * @param millis     Total time to read from file
         * @return Number of reads in the given amount of time
         * @throws IOException
         */
        public int randomReadFixedTime(String filePath, int bufferSize, int millis) throws IOException {
            // file to read from
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            // size of file
            long fileSize = (long) (file.getChannel().size() % Long.MAX_VALUE);
            // counter for number of reads
            int counter = 0;
            // buffer for reading
            byte[] bytes = new byte[bufferSize];
            Random rand = new Random();
            long start = System.nanoTime();
            // read for a fixed amount of time
            while (System.nanoTime() - start < millis * 1_000_000) {
                long random = ThreadLocalRandom.current().nextLong(fileSize);
                file.seek(random);
                // read the bytes into buffer
                file.read(bytes);
                counter++;
            }

            file.close();
            return counter;
        }

        /**
         * Read data from a file at a specific position
         *
         * @param filePath Path to file
         * @param position Position in file
         * @param size     Number of bytes to reads from the given position
         * @return Data that was read
         * @throws IOException
         */
        public byte[] readFromFile(String filePath, int position, int size)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            byte[] bytes = new byte[size];
            file.read(bytes);
            file.close();
            return bytes;
        }

        /**
         * Write data to a file at a specific position
         *
         * @param filePath Path to file
         * @param data     Data to be written
         * @param position Start position in file
         * @throws IOException
         */
        public void writeToFile(String filePath, String data, int position)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            file.write(data.getBytes());
            file.close();
        }
    }

}
