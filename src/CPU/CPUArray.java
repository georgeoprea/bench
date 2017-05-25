package CPU;

import bench.IBenchmark;

public class CPUArray implements IBenchmark {
    private int size;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    public void myArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++)
            array[i] = i;
        for (int i = 1; i < array.length / 2; i++)
            array[i] = array[array[i] * array[i - 1]];
    }

    @Override
    public void run() {
        this.myArray(size);
    }

    @Override
    public void warmUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(int option) {
        int _size = size;
        size = 10000000;
        this.run();
        this.run();
        this.run();
        size = _size;

    }

    @Override
    public void warmUp(Object option) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clean() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void run(Object[] options) {
        // TODO Auto-generated method stub

    }
}
