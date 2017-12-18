package PriorityTester;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by girijah on 12/18/2017.
 */
public class ThreadScheduler extends Applet implements Runnable {

    Thread thread;

    Runner runner1;
    Runner runner2;
    Runner runner3;


    @Override
    public void run() {

        while(true) {
            repaint();
        }
    }


    @Override
    public void init() {
        setSize(1400, 400);

        thread = new Thread(this);

        runner1 = new Runner(50, Color.black);
        runner2 = new Runner(150, Color.blue);
        runner3 = new Runner(250, Color.green);

        runner1.setPriority(1);
        runner2.setPriority(2);
        runner3.setPriority(10);

        runner1.setName("A");
        runner2.setName("B");
        runner3.setName("C");
    }


    @Override
    public void start() {

        thread.start();

        runner1.start();
        runner2.start();
        runner3.start();

    }


    @Override
    public void paint(Graphics g) {
        runner1.paint(g);
        runner2.paint(g);
        runner3.paint(g);
    }

}
