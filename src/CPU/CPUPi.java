package CPU;

import java.math.BigDecimal;

import bench.IBenchmark;

import java.math.*;

public class CPUPi implements IBenchmark {
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");
    private int size;

    public static BigDecimal pi(int numDigits) {
        int calcDigits = numDigits + 10;

        return FOUR.multiply((FOUR.multiply(arccot(FIVE, calcDigits)))
                .subtract(arccot(TWO_THIRTY_NINE, calcDigits)))
                .setScale(numDigits, RoundingMode.DOWN);
    }

    private static BigDecimal arccot(BigDecimal x, int numDigits) {
        BigDecimal unity = BigDecimal.ONE.setScale(numDigits, RoundingMode.DOWN);
        BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
        BigDecimal xpower = new BigDecimal(sum.toString());
        BigDecimal term = null;

        boolean add = false;

        for (BigDecimal n = new BigDecimal("3"); term == null ||
                term.compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
            xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
            term = xpower.divide(n, RoundingMode.DOWN);
            sum = add ? sum.add(term) : sum.subtract(term);
            add = !add;
        }
        return sum;
    }

    @Override
    public void initialize(int size) {
        this.size = size;

    }

    @Override
    public void run() {
        System.out.println(this.pi(size));
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
    public void clean() {
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
