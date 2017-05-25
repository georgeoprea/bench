package logging;

public interface ILog {
    /**
     * Writes string to output.
     *
     * @param string
     */
    public void write(String string);

    /**
     * Writes long value to output.
     *
     * @param value
     */
    public void write(long value);

    /**
     * Writes elapsed time in unit format.
     *
     * @param value
     * @param string
     * @param unit
     */
    public void writeTime(String string, long value, TimeUnit unit);

    /**
     * Writes an array of objs to stream.
     *
     * @param values
     */
    public void write(Object... values);

    //public void writeTime(long value, TimeUnit unit);


    /**
     * Closes the write stream.
     */
    public void close();

}
