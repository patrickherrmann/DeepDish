package deepdish;

/**
 * @author Patrick Herrmann
 */
public abstract class LoopThread extends Thread {
    
    private int rate;
    private boolean running = false;
    
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
        
    }
    
    public abstract void performTask();
}
