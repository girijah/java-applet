package Applet;

/**
 * Created by girijah on 12/18/2017.
 */


import java.awt.*;
import java.applet.Applet;

public class RunnerApplet extends    Applet
        implements Runnable
{

    final static int NUMRUNNERS = 3;
    final static int SPACING    = 40;

    Thread updateThread;

    // An "ARRAY of THREADS"
    Runner runners[] = new Runner[NUMRUNNERS];

    public void init()
    {
        // Get argument value of HTML tag "type"
        //String raceType = getParameter("type");

        for ( int i = 0; i < NUMRUNNERS; i++ )
        {
            runners[i] = new Runner(i);

//            if ( raceType.compareTo("unfair") == 0 )
//                runners[i].setPriority(i+1);
//            else
//                runners[i].setPriority(2);

            if ( i == 0 )
                runners[i].setPriority(1);
            else
                runners[i].setPriority(i+1);
        }

        if ( updateThread == null )
        {
            // "this" is the runnable target
            updateThread = new Thread(this, "Thread Race");
            updateThread.setPriority( NUMRUNNERS + 1 );
        }
    }


    public boolean mouseDown( java.awt.Event evt, int x, int y )
    {
        if ( !updateThread.isAlive() )
        {
            updateThread.start();
        }

        for ( int i = 0; i < NUMRUNNERS; i++ )
        {
            if ( !runners[i].isAlive() )
            {
                runners[i].start();
            }
        }

        return true;
    }


    public void paint( Graphics g )
    {

        g.setColor(Color.lightGray);

        g.fillRect(0, 0, size().width, size().height);

        g.setColor(Color.red);

        for ( int i = 0; i < NUMRUNNERS; i++ )
        {
            int pri = runners[i].getPriority();

            g.drawString(runners[i].getName() +
                            "[PRI = " + new Integer(pri).toString() +"]",
                    0,
                    ( i + 1 ) * SPACING );
        }

        update(g);

    }


    public void update(Graphics g)
    {
        for (int i = 0; i < NUMRUNNERS; i++)
        {
//            g.drawLine( SPACING, ( i + 1 ) * SPACING, SPACING + ( runners[i].tick / 10000 ), ( i + 1 ) * SPACING );
            g.drawLine( SPACING*3, ( i + 1 ) * SPACING, SPACING + ( runners[i].tick / 10000 ), ( i + 1 ) * SPACING );

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


    // Provides body for "updateThread"
    public void run()
    {
        while ( updateThread != null )
        {
            repaint();

            try {
                updateThread.sleep(1500);
            }
            catch (InterruptedException e) { }
        }
    }

    // Applet's stop method
    public void stop()
    {
        for (int i = 0; i < NUMRUNNERS; i++)
        {
            // Terminate Runner thread
            if ( runners[i].isAlive() )
            {
                runners[i].terminate = true ;
                runners[i] = null;
            }
        }

        if ( updateThread.isAlive() )
        {
            // Terminate updateThread
            updateThread = null;
        }
    }
}

