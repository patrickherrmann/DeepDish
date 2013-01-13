package windowed;

import deepdish.Animator;
import deepdish.Painter;
import deepdish.Scene;
import deepdish.Viewport;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Patrick Herrmann
 */
public class DeepDishFrame extends JFrame {
    
    private Animator animator;
    private Painter painter;
    private Scene scene;
    private JLabel canvas;
    
    public static void main(String[] args) {
        Scene s = new Scene();
        for (int i = 0; i < 100; i++) {
            s.add(new Circle(5.7));
        }
        new DeepDishFrame(s, 640, 480);
    }
    
    public DeepDishFrame(Scene scene, int width, int height) {
        super("Deep Dish Window");
        this.scene = scene;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initializeAnimation(width, height);
    }
    
    private void initializeAnimation(int width, int height) {
        Viewport viewport = new Viewport(width, height);
        animator = new Animator(scene, 80);
        painter = new DeepDishFramePainter(scene, viewport, 40);
        canvas = new JLabel(new ImageIcon(viewport.getImage()));
        add(canvas);
        pack();
        animator.start();
        painter.start();
    }
    
    private class DeepDishFramePainter extends Painter {
        
        public DeepDishFramePainter(Scene scene, Viewport viewport, int fps) {
            super(scene, viewport, fps);
        }

        @Override
        public void sendToScreen(Image buffer) {
            repaint();
        }
    }
    
    
}
