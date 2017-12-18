package PriorityTester;

import java.awt.*;

/**
 * Created by girijah on 12/18/2017.
 */
public class Runner extends Thread {

    private int x = 0;
    private int y = 0;
    private int radius = 10;
    private Graphics g;
    private Color c;
    private int grow;


    public Runner(int y, Color color) {
        this.y = y;
        this.c = color;
    }


    public void paint(Graphics g) {
        this.g = g;
        this.g.setColor(c);

        //this.g.fillOval(x, y, radius, radius);

        g.drawLine( x, y, this.grow, y );
    }


    public void update(int grow) {
            x+=grow;
    }


    @Override
    public synchronized void run() {

        for(int i = 0; i<100; i++) {
            update(i);
            this.grow = i;

            if(i==99) {
              System.out.println(getName()+" finished at "+System.currentTimeMillis());
            }
            try {
                Thread.sleep(150);

            } catch (InterruptedException e) {
                System.out.println("Error Occured!");
            }
        }
    }
}
