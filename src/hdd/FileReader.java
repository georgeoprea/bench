package hdd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.nio.*;

import timing.Timer;

public class FileReader {

    private Timer timer = new Timer();
    private double benchScore;

    /**
     * Read file benchmark using java.io
     */
    public void streamReadFixedSize(String fileName, int minBufSize, int maxBufSize) throws IOException {

        System.out.println("Stream read benchmark");
        int currentBufferSize = minBufSize;

        int counter = 0;

        while (currentBufferSize <= maxBufSize) {
            readWithBufferSize(fileName, currentBufferSize);
            currentBufferSize *= 2;
            counter++;
        }

        benchScore /= counter;
        System.out.println("File read score : " + String.format("%.2f", benchScore) + " MB/sec");
    }

    /**
     * Read file benchmark using java.nio
     */
    public void nIOReadFixedSize(String fileName, int minBufSize, int maxBufSize) throws IOException {

        System.out.println("NIO read");
        int myBufferSize = minBufSize;
        while (myBufferSize <= maxBufSize) {
            timer.start();
            long totalBytes = readNio(fileName, myBufferSize);

            if (totalBytes != -1) {
                printStats(fileName, totalBytes, myBufferSize);
            } else {
                System.out.println("File are not equal");
            }

            myBufferSize *= 2;
        }
    }

    /**
     * Read given file with a buffered stream (io)
     */
    private void readWithBufferSize(String fileName, int myBufferSize) throws IOException {

        if (!(new File(fileName).exists()))
            throw new IOException("Benchmark file " + fileName + " was not found");

        final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)),
                myBufferSize);
        byte[] buffer = new byte[myBufferSize];
        int read;

        timer.start();
        long totalbytes = 0;
        while ((read = inputStream.read(buffer)) != -1) {
            totalbytes += read;
        }
        if (read == -1) {
            printStats(fileName, totalbytes, myBufferSize);
        } else {
            System.out.println("Error reading " + fileName);
        }
        inputStream.close();
    }

    public void compareWithBufferSize(String fileName1, String fileName2, int myBufferSize) throws IOException {
        final BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(new File(fileName1)),
                myBufferSize);
        byte[] buff1 = new byte[myBufferSize];
        final BufferedInputStream inputStream2 = new BufferedInputStream(new FileInputStream(new File(fileName2)),
                myBufferSize);
        byte[] buff2 = new byte[myBufferSize];
        int read1;

        System.out.println("File compare benchmark");

        timer.start();
        long totalBytes = 0;
        while ((read1 = inputStream1.read(buff1)) != -1) {
            totalBytes += 2 * read1;
            int read2 = inputStream2.read(buff2);
            if (read1 != read2) {
                break;
            }
            if (!Arrays.equals(buff1, buff2)) {
                break;
            }
        }
        if (read1 == -1) {
            printStats(fileName1, totalBytes, myBufferSize);
        } else {
            System.out.println("Files are not equal");
        }
        inputStream1.close();
        inputStream2.close();
    }

    /**
     * Read given file using a file channel (nio)
     */
    /*
     * private void byteChannelTrial(String fileName) throws Exception { try
	 * (FileChannel channel = (FileChannel) Files.newByteChannel(this.fileName,
	 * READ)) { ByteBuffer buffer = ByteBuffer.allocate(1024); while
	 * (channel.read(buffer) != -1) { buffer.flip();
	 * System.out.print(Charset.defaultCharset().decode(buffer));
	 * buffer.clear(); } } }
	 */
    private long readNio(String fileName, int myBufferSize) throws IOException {
        FileChannel fChannel = null;
        long totalBytes = 0;

        try {
            fChannel = new FileInputStream(fileName).getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(myBufferSize);
            int read;

            while ((read = fChannel.read(buffer)) != -1) {
                buffer.clear();
                totalBytes += read;
            }
            return totalBytes;
        } finally {
            if (fChannel != null) {
                fChannel.close();
            }
        }
    }

    public void compareNIO(String fileName1, String fileName2, int myBufferSize) throws IOException {
        //RandomAccessFile file1 = new RandomAccessFile(fileName1, "r");
        //RandomAccessFile file2 = new RandomAccessFile(fileName2, "r");
        FileChannel fChannel1 = new FileInputStream(fileName1).getChannel();
        FileChannel fChannel2 = new FileInputStream(fileName2).getChannel();

        long totalBytes = 0;

        System.out.println("File compare benchmark using NIO");

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(myBufferSize);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(myBufferSize);

        int read1;
        while ((read1 = fChannel1.read(buffer1)) != -1) {
            totalBytes += 2 * read1;
            int read2 = fChannel2.read(buffer2);
            if (read1 != read2) {
                break;
            }
            if (buffer1.equals(buffer2)) {
                break;
            }
            buffer1.clear();
            buffer2.clear();

        }

        if (read1 == -1)
            printStats(fileName1, totalBytes, myBufferSize);
        else
            System.out.println("Files not equal");
        fChannel1.close();
        fChannel2.close();
        //file1.close();
        //file2.close();
    }

    private void printStats(String fileName, long totalBytes, int myBufferSize) {
        NumberFormat nf = new DecimalFormat("#.00");
        final long time = timer.stop();
        double mseconds = time / 1000000d;
        double megabytes = totalBytes / 1024 / 1024;
        double rate = (megabytes) / mseconds * 1000;
        System.out.println("Done reading " + totalBytes + " bytes from file: " + fileName + " in " + nf.format(mseconds)
                + " ms (" + nf.format(rate) + "MB/sec)" + " with a buffer size of " + myBufferSize / 1024 + " kB");

        // actual score (MBps)
        benchScore += rate;
    }
}