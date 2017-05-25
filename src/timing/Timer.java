package timing;

public class Timer implements ITimer {
    private long elapsed = 0;
    private long stored = 0;
    //private long startTime = 0;
    //private long pauseTime = 0;
    //private long endTime   = 0;

    @Override
    public void start() {
        stored = System.nanoTime();
        //starTime = System.nanoTime();

    }

    @Override
    public long stop() {
        /*elapsed = System.nanoTime() - elapsed;
        stored += elapsed;*/
        return System.nanoTime() - stored;
        //return endTime += System.nanoTime() - startTime;
    }

    @Override
    public long pause() {
        elapsed = System.nanoTime() - elapsed;
        stored += elapsed;
        return elapsed;
        //return endTime += System.nanoTime() - startTime;
    }

    @Override
    public void resume() {
        elapsed = System.nanoTime();
        //startTime = System.nanoTime();
    }

}
