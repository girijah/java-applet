package JavaApplet;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by girijah on 12/10/2017.
 */

public class MainClass extends Applet implements Runnable {

    Thread thread = new Thread(this);
    boolean running = true;

    Player p;

    Image doublebufferImage;
    Graphics doublebuffergraphics;

    public void init() {
        setSize(600, 500);
        p = new Player();
    }

    public void start() {
        thread.start();
    }

    public void update(Graphics g) {
        doublebufferImage = createImage(getWidth(), getHeight());
        doublebuffergraphics = doublebufferImage.getGraphics();
        paint(doublebuffergraphics);
        g.drawImage(doublebufferImage,0,0, this);
    }

    public void paint(Graphics g) {
        p.paint(g);
    }

    public void destroy() {
        running = false;
    }

    public void stop() {
        running = false;
    }


    @Override
    public void run() {
        while(running) {
            repaint();

            p.update(this);

            try {
                Thread.sleep(20);

            } catch (InterruptedException e) {
                System.out.println("Error Occured!");
            }
        }
    }
}
