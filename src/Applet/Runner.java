package Applet;

/**
 * Created by girijah on 12/18/2017.
 */

class Runner extends Thread
{
    // Used to TERMINATE the thread
    public volatile boolean terminate = false ;

    public int tick = 1;

    public Runner( int id )
    {
        super( "Runner-" + id ) ;
    }

    public void run()
    {
        while ( (tick < 400000000) && !terminate )
        {
            tick++ ;
        }
    }

}
