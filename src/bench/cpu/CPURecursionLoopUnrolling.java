package bench.cpu;

import bench.IBenchmark;

public class CPURecursionLoopUnrolling implements IBenchmark {

    private int size;
    private long result;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
        recursive(1, 1000);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Not implemented. Use run with options instead.");
    }

    @Override
    public void run(Object... options) {
        boolean unrolled = (Boolean) options[0];
        int unrollLevel = 0;
        if (unrolled)
            unrollLevel = (Integer) options[1];

        if (unrolled == false) {
            long urL = recursive(unrollLevel, this.size);
            System.out.println("Sum of prime numbers(recursive): " + urL);
        } else {
            long Unrolled = recursiveUnrolled(2, unrollLevel, this.size);
            System.out.println("Sum of prime numbers(recursiveunrolled): " + Unrolled);
        }
    }

    private long recursive(long i, long n) {
        long prime = findNextPrime(i);

        if (prime >= n)
            return 0;
        try {
            return prime + recursive(prime + 1, n);
        } catch (StackOverflowError e) {
            System.out.println(prime);
            // print prime reached and/or number of calls
            return 0;
        }
    }

    private long recursiveUnrolled(long start, long l, long n) {
        long sum = 0, x = 0;
        for (; l > 0; l--) {
            x = findNextPrime(start);
            System.out.println(x);
            start = x + 1;
            sum = x + sum;
        }
        return sum;
        // at each recursive 'step' find the next 'l' primes, not just one
    }

    private long findNextPrime(long number) {
        for (; ; number++) {
            if (isPrime(number)) {
                return number;
            }
        }
    }

    private boolean isPrime(long number) {
        if (number == 2)
            return true;

        if (number % 2 == 0)
            return false;

        for (long j = 3; j <= Math.sqrt(number); j += 2)
            if (number % j == 0)
                return false;

        return true;
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

}
