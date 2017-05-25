package CPU;

import bench.IBenchmark;

public class CPUFixedPointTest implements IBenchmark {

    private int result;
    private int size;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
        int oldsize = size;
        size = 100000;
        run(NumberRepresentation.FIXED);
        run(NumberRepresentation.FLOATING);
        size = oldsize;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object) instead");
    }

    @Override
    public void run(Object... options) {
        result = 0;

        switch ((NumberRepresentation) options[0]) {
            case FLOATING:
                for (int i = 0; i < size; ++i)
                    result += (int) (i / 256.0);
                break;
            case FIXED:
                for (int i = 0; i < size; ++i)
                    result += i >> 8;
                break;
            default:
                break;
        }
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
