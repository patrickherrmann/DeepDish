package deepdish;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Patrick Herrmann
 */
public final class Scene implements Paintable, Updatable {
    
    // List of all game objects
    private final List<GameObject> gameObjects = new ArrayList<GameObject>();
    
    public void add(GameObject gameObject) {
        synchronized (gameObjects) {
            gameObjects.add(gameObject);
        }
    }
    
    @Override
    public void update() {
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
    public void paint(Viewport v) {
        // Clear canvas
        v.getGraphics().setColor(Color.WHITE);
        v.getGraphics().fillRect(0, 0, v.getImage().getWidth(null), v.getImage().getHeight(null));
        
        // Paint game objects
        synchronized (gameObjects) {
            // Sort by z-index
            Collections.sort(gameObjects);
            for (GameObject gameObject : gameObjects) {
                gameObject.paint(v);
            }
        }
    }
}
