package CPU;

import bench.*;
import timing.*;
import logging.*;


public class CPUThRoots implements IBenchmark {
    private double result;
    private int size;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    public void warmup() {
        run(2);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options) {

        int nThreads = (int) options[0];
        Thread[] threads = new Thread[nThreads];
        final int jobPerThread = size / nThreads;

        for (int i = 0; i < nThreads; ++i) {
            threads[i] = new Thread(new SquareRootTask(i * jobPerThread, (i + 1) * jobPerThread));
            threads[i].start();
        }

        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public String getScore() {
        ILog log = new ConsoleLog();
        Timer t = new Timer();
        double score = 0;
        for (int nThreads = 1; nThreads <= 32; nThreads = nThreads * 2) {
            t.start();
            this.run(nThreads);
            long militime = TimeUnit.Milli.toTimeUnit(t.stop(), TimeUnit.Milli);
            score += size / (militime * nThreads);
            log.write(nThreads + " threads in " + militime + "ms. Score : " + size / (militime * nThreads));
        }
        return score / 6 + "";
    }

    public String getResult() {
        return String.valueOf(result);
    }

    public synchronized void add(int sum) {
        this.result += sum;
    }

    public void stop() {
        // TODO Auto-generated method stub

    }

    class SquareRootTask implements Runnable {
        private int from, to;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = from; i < to; i++)
                sum += getNewtonian(i);
            add(sum);

        }

        private double getNewtonian(double x) {
            if (x < 0) return Double.NaN;
            double EPSILON = 1E-4;
            double t = x;
            while (Math.abs(t - x / t) > EPSILON * t)
                t = (x / t + t) / 2.0;
            return t;
        }

    }
}