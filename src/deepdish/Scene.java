package deepdish;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Patrick Herrmann
 */
public abstract class Scene implements Paintable {
    
    // Set of all game objects, synchronized and maintained by z-index for easy rendering
    protected final SortedSet<GameObject> gameObjects = Collections.synchronizedSortedSet(new TreeSet<GameObject>());
    
    public final void update() {
        synchronized (gameObjects) {
            Iterator<GameObject> iter = gameObjects.iterator();
            while (iter.hasNext()) {
                GameObject next = iter.next();
                if (next.isDestroyed()) {
                    iter.remove();
                } else {
                    next.update();
                }
            }
        }
    }

    @Override
    public final void paint(Viewport v) {
        // Paint backdrop
        paintBackground(v);
        
        // Paint game objects
        synchronized (gameObjects) {
            for (GameObject gameObject : gameObjects) {
                gameObject.paint(v);
            }
        }
    }
    
    public abstract void paintBackground(Viewport v);
}
