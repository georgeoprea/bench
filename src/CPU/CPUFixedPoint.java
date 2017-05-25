package CPU;

import bench.IBenchmark;

public class CPUFixedPoint implements IBenchmark {
    private int size;

    public void run(Object... options) {
        switch ((Branches) options[0]) {
            case doArithmetic:
                this.doArithmetic();
                break;
            case doBranching:
                this.doBranching();
                break;
            case doArray:
                this.doArray();
                break;
        }
    }

    public void doArithmetic() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += 25 * 6;
            sum = sum << 5;
            sum = 32 + (sum << 3);
        }
        sum = sum * (sum - sum / 2) + 32;
    }

    public void doBranching() {
        int b = 0;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                b++;
                if (i % 3 == 0)
                    b++;
            } else if (i % 3 == 0)
                b--;
        }
    }

    public void doArray() {
        int[] array = new int[this.size];

        for (int i = 0; i < array.length; i++)
            array[i] = i;
        for (int i = 1; i < array.length / 2; i++)
            array[i] = array[array[i] * array[i - 1]];
    }

    @Override
    public void initialize(int size) {
        this.size = size;

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp(Object option) {
        this.run(option);
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


}