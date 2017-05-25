package hdd;

import java.io.IOException;

import bench.IBenchmark;

public class HDDWriteSpeed implements IBenchmark {

    @Override
    public void initialize(int size) {
    }

    @Override
    public void warmUp() {
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Object) instead");
    }

    @Override
    public void run(Object... options) {
        FileWriter writer = new FileWriter();
        // either "fs" - fixed size, or "fb" - fixed buffer
        String option = (String) options[0];
        // true/false whether the written files should be deleted at the end
        Boolean clean = (Boolean) options[1];

        String prefix = "/Users/georgeoprea/Desktop/file";
        String suffix = ".dat";
        int startIndex = 0;
        int endIndex = 8;

        try {
            if (option.equals("fs"))
                writer.streamWriteFixedSize(prefix, suffix, startIndex,
                        endIndex, 256 * 1024 * 1024, clean);
            else if (option.equals("fb"))
                writer.streamWriteFixedBuffer(prefix, suffix, startIndex,
                        endIndex, 4 * 1024, clean);
            else
                throw new IllegalArgumentException("Argument "
                        + options[0].toString() + " is undefined");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void warmUp(Object option) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(int option) {
        // TODO Auto-generated method stub

    }
}
