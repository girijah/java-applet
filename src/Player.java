import sun.applet.Main;

import java.awt.*;

/**
 * Created by girijah on 12/10/2017.
 */
public class Player {

    private int x = 50;
    private int y = 50;
    private int radius = 20;
    private Graphics g;

    public void update(MainClass mc) {

        if(x < mc.getWidth()-50) { // x < 550
            x+=5;
        }
        else if(y < mc.getHeight()-50){ // y < 450
            y+=5;
        }

    }


    public void paint(Graphics g) {
        this.g = g;
        this.g.setColor(Color.BLUE);

        if (y==450) {
            changeColor();
        }

        this.g.fillOval(x, y, radius*2, radius*2);
    }


    private void changeColor() {
        this.g.setColor(Color.orange);
    }


}
