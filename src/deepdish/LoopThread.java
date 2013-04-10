package deepdish;

/**
 * @author Patrick Herrmann
 */
public abstract class LoopThread extends Thread {
    
    private double rate;
    private volatile boolean running = false;
    
    protected LoopThread(String name, double rate) {
        super(name);
        this.rate = rate;
    }
    
    public double getRate() {
        return rate;
    }
    
    @Override
    public void start() {
        super.start();
        running = true;
    }
    
    @Override
    public void run() {
        long period = (long)(1000 / rate);
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

    public void terminate() {
        running = false;
    }
    
    public abstract void performTask();
}
