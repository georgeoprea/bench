package CPU;

import bench.IBenchmark;

public class CPUThread implements IBenchmark {
    private int size;
    private int nThreads; //No of Threads
    private Thread[] threads;
    private double result;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(Object... option) {
        if (option[0] instanceof Integer) {
            this.nThreads = ((Integer) option[0]).intValue();
            this.threads = new Thread[this.nThreads]; //set size

            for (int i = 0; i < nThreads; i++) {
                SquareRoot sqrt = new SquareRoot(i * size / nThreads, (i + 1) * size / nThreads, i + 1);
                threads[i] = new Thread(sqrt);
                threads[i].start();
            }

            for (int i = 0; i < nThreads; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        } else {
            System.out.println("Bad input");
        }
    }

    public synchronized void add(double sum) {
        this.result += sum;
    }

    @Override
    public void warmUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(Object option) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(int option) {
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

    public class SquareRoot implements Runnable {
        private int start;
        private int end;
        private int id;

        public SquareRoot(int start, int end, int id) {
            this.id = id;
            this.end = end;
            this.start = start;
        }

        @Override
        public void run() {
            double sum = 0;
            for (int i = start; i < end; i++)
                sum += computeSQRT(i);
            add(sum);

        }

        private double computeSQRT(double c) {
            if (c < 0)
                return Double.NaN;
            double EPSILON = 1E-15;
            double t = c;
            while (Math.abs(t - c / t) > EPSILON * t)
                t = (c / t + t) / 2.0;
            return t;
        }
    }


}

