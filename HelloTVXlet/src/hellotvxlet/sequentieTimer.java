package hellotvxlet;
import java.util.Timer;
import java.util.TimerTask;
import org.dvb.ui.DVBColor;
import org.havi.ui.*;


public class sequentieTimer extends TimerTask{
    
    private final int serial;
    
    sequentieTimer (int serial)
    {
        this.serial = serial;
    }
    
    public void run()
    {
                 MijnTimerTask objMijnTimerTask = new MijnTimerTask();
                 Timer timer = new Timer();
                 
                 if(HelloTVXlet.comArr[serial]==1)
                 {
                     System.out.println("Groen");
                     HelloTVXlet.GroenSecondlicht();
                     timer.schedule(objMijnTimerTask, 1000);
                 }
                 if(HelloTVXlet.comArr[serial]==2)
                 {
                     System.out.println("Rood");
                     HelloTVXlet.RoodSecondLicht();
                     timer.schedule(objMijnTimerTask, 1000);
                 }
                 if(HelloTVXlet.comArr[serial]==3)
                 {    
                     System.out.println("Geel");
                     HelloTVXlet.GeelSecondLicht();
                     timer.schedule(objMijnTimerTask, 1000);
                 }
                 if(HelloTVXlet.comArr[serial]==4)
                 {
                     System.out.println("Blauw");
                     HelloTVXlet.BlauwSecondLicht();
                     timer.schedule(objMijnTimerTask, 1000);
                 }
                 HelloTVXlet.scene.repaint();
                
                 synchronized(this){notify();}
    }
}        
     
                
    

