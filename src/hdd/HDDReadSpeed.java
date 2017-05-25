package hdd;

import java.io.IOException;

import bench.IBenchmark;

//64 kb and 1MB buffers for reading (the 1kB buffer is hardcoded somewhere)

public class HDDReadSpeed implements IBenchmark {

    @Override
    public void initialize(int size) {
    }

    @Override
    public void run() {
    }

    @Override
    public void run(Object... options) {

        try {
            ReadOptions option = (ReadOptions) options[0];
            FileReader reader = new FileReader();
            int minBufferSize = 1024; // 1KB
            int maxBufferSize = 4 * 1024 * 1024; // 4MB
            String sourcePath = "/Users/georgeoprea/Desktop/Rogue.One.2016.PROPER.720p.BluRay.DD5.1.x264-DON/Rogue.One.2016.PROPER.720p.BluRay.DD5.1.x264-DON.mkv";
            String sourcePath2 = "/Users/georgeoprea/Desktop/kali-copy.img.xz";

            switch (option) {
                case STREAM:
                    reader.streamReadFixedSize(sourcePath, minBufferSize, maxBufferSize);
                    //reader.compareWithBufferSize(sourcePath, sourcePath2, 256 * 1024);
                    break;
                case NIO:
                    reader.nIOReadFixedSize(sourcePath, minBufferSize, maxBufferSize);
                    //reader.compareNIO(sourcePath, sourcePath2, 256 * 1024);
                    break;

            }

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
    }

    public String getResult() {
        return null;
    }

    public void stop() {
        // TODO Auto-generated method stub

    }

    public enum ReadOptions {
        STREAM, NIO;
    }

}
