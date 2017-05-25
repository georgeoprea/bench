package logging;

public enum TimeUnit {
    Nano, Micro, Milli, Sec;

    /**
     * Converts from ns to other time units.
     */
    public static long toTimeUnit(long time, TimeUnit unit) {
        switch (unit) {
            case Nano:
                return time;
            case Micro:
                return time / 1000;
            case Milli:
                return time / 1000000;
            case Sec:
                return time / 1000000000;
            default:
                return time;
        }
    }
}
