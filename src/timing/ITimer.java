package timing;

public interface ITimer {
    /**
     * Starts a new timer. <br>
     */
    public void start();

    /**
     * Stops the timer. <br>
     *
     * @return Returns the time in ns since <b> start </b> was called.
     */
    public long stop();

    /**
     * Pauses the timer.
     *
     * @return Returns the time in ns since <b> start </b> was called.
     */
    public long pause();

    /**
     * Resumes the timer. <br>
     */
    public void resume();
}
