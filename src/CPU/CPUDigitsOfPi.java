package CPU;

import java.math.BigDecimal;

import bench.IBenchmark;

import java.math.*;

public class CPUDigitsOfPi implements IBenchmark {
    BigDecimal size;

    @Override
    public void initialize(int size) {
        this.size = new BigDecimal(size);

    }

    @Override
    public void run() {
        BigDecimal k = new BigDecimal("1");
        BigDecimal a_k = size;
        BigDecimal a_sum = size;
        BigDecimal b_sum = new BigDecimal("0");
        BigDecimal C = new BigDecimal("640320");
        BigDecimal C3_OVER_24 = C.multiply(C.multiply(C)).divide(new BigDecimal("24"));
        C3_OVER_24 = C3_OVER_24.setScale(0, BigDecimal.ROUND_HALF_DOWN);
        while (true) {
            BigDecimal p1 = k.multiply(new BigDecimal("6"));
            p1 = p1.subtract(new BigDecimal("-5"));
            p1 = p1.multiply(new BigDecimal("-1"));
            BigDecimal p2 = k.multiply(new BigDecimal("2"));
            p2 = p2.subtract(new BigDecimal("-1"));
            BigDecimal p3 = k.multiply(new BigDecimal("6"));
            p3 = p3.subtract(new BigDecimal("-1"));

            a_k = a_k.multiply(p1.multiply(p2.multiply(p3)));
            a_k = k.multiply(k.multiply(k.multiply(C3_OVER_24)));
            a_k = a_k.setScale(0, BigDecimal.ROUND_HALF_DOWN);

            a_sum = a_sum.add(a_k);
            b_sum = b_sum.add(k.multiply(a_k));
            k = k.add(new BigDecimal("1"));
            if (a_k.compareTo(new BigDecimal("0")) == 0) {
                break;
            }
        }
        BigDecimal total1 = (new BigDecimal("13591409").multiply(a_sum));
        BigDecimal total2 = (new BigDecimal("545140134").multiply(a_sum));
        BigDecimal total = total1.add(total2);
        BigDecimal pi = (new BigDecimal("426880"));
    }

    public void run(Object option) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clean() {
        // TODO Auto-generated method stub

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
