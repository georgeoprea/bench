package CPU;

import java.util.Random;

import bench.IBenchmark;

public class CPURecursionVsLooping implements IBenchmark {

    private int size;
    private long result;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
    }

    @Override
    @Deprecated
    public void run() {
        throw new UnsupportedOperationException(
                "Not implemented. Use run with options instead.");
    }

    @Override
    public void run(Object... options) {
        if (options.length == 1) {
            if (options[0] instanceof Looping) {
                switch ((Looping) options[0]) {
                    case ITERATIVE:
                        result = doSomethingIterative(this.size);
                        break;
                    case RECURSIVE:
                        result = doSomethingRecursive(this.size);
                        break;
                }
            } else {
                throw new IllegalArgumentException(
                        "Argument must be of type 'Looping'");
            }
        } else {
            throw new IllegalArgumentException(
                    "Benchmark needs only one argument of type 'Looping'");
        }
    }

    private int doSomethingIterative(int num) {
        int fact = 0;
        for (int i = 1; i <= num; i++)
            fact = fact * i;
        return fact;
    }

    private int doSomethingRecursive(int num) {
        if (num == 1) {
            return 1;
        }
        return num + (doSomethingRecursive(num - 1));
    }

    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    @Override
    public void warmUp(Object option) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(int option) {
        // TODO Auto-generated method stub

    }

    public enum Looping {
        RECURSIVE, ITERATIVE
    }
}
