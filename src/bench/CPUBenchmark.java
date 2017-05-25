package bench;

public class CPUBenchmark implements IBenchmark {
    private int size;

    public void initialize(int size) {
        this.size = size;
    }

    public void run() {
        int i, j, k, s = 0;

        for (i = 0; i < size - 2; i++)
            for (j = i + 1; j < size - 1; j++)
                for (k = j + 1; k < size; k++)
                    s++;
    }

    public void run(Object o) {
    }

    public void clean() {
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
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void run(Object[] options) {
        // TODO Auto-generated method stub

    }


}
