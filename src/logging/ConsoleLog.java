package logging;

public class ConsoleLog implements ILog {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void write(long value) {
        //System.out.println(String.valueOf(value));
        System.out.println(value + "");
    }

    @Override
    public void writeTime(String string, long value, TimeUnit unit) {
        System.out.println(string + TimeUnit.toTimeUnit(value, unit) + " " + unit);
    }

    @Override
    public void write(Object... values) {
        for (Object o : values)
            System.out.print(o.toString() + " ");
        System.out.println();
    }

    @Override
    public void close() {
    }
}