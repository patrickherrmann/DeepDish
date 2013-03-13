package deepdish;

/**
 * @author Patrick Herrmann
 */
public abstract class LoopThread extends Thread {
    
    private int rate;
    private volatile boolean running = false;
    
    protected LoopThread(String name, int rate) {
        super(name);
        this.rate = rate;
    }
    
    public int getRate() {
        return rate;
    }
    
    @Override
    public void start() {
        super.start();
        running = true;
    }
    
    @Override
    public void run() {
        long period = 1000 / rate;
        long before, diff, sleepTime;
        while (running) {
            before = System.currentTimeMillis();

            performTask();

            diff = period - (System.currentTimeMillis() - before);
            sleepTime = diff > 0 ? diff : 3;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public abstract void performTask();
}
