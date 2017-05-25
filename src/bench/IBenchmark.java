package bench;

public interface IBenchmark {
    /**
     * This call will initialize our benchmark. <br>
     *
     * @param size - Size of data to be initialized
     */
    public void initialize(int size);

    /**
     * This will run the benchmark. <br>
     */
    public void run();

    /**
     * Benchmarking Algorithm.
     *
     * @param option - Pass a benchmark option <br>
     */
    public void run(Object... option);

    /**
     * This call will release allocated data. <br>
     */
    public void warmUp();

    public void warmUp(Object option);

    public void warmUp(int option);

    public void clean();

    public String getResult();


}
