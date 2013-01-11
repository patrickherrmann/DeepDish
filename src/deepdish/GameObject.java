package deepdish;

/**
 * @author Patrick Herrmann
 */
public abstract class GameObject implements Paintable, Comparable<GameObject> {
    
    private boolean destroyed = false;
    private int zIndex = 0;
    
    public abstract void update();
    
    public void destroy() {
        destroyed = true;
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }
    
    @Override
    public int compareTo(GameObject other) {
        return zIndex - other.zIndex;
    }
}
