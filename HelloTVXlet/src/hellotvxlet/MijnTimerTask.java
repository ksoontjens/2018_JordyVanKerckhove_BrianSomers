package hellotvxlet;
import java.util.TimerTask;
import org.dvb.ui.DVBColor;
import org.havi.ui.*;

public class MijnTimerTask extends TimerTask {
    public void run()
    {
     System.out.println("Timer method");
    
     HelloTVXlet.Groen = new HTextButton(" ");
     HelloTVXlet.Groen.setSize(360,290);
     HelloTVXlet.Groen.setLocation(0,0);
     HelloTVXlet.Groen.setBackground(new DVBColor(HelloTVXlet.donkerGroen));
     HelloTVXlet.Groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
     HelloTVXlet.scene.add(HelloTVXlet.Groen);
     HelloTVXlet.scene.popToFront(HelloTVXlet.Groen);
        
     HelloTVXlet.Rood = new HTextButton(" ");
     HelloTVXlet.Rood.setSize(360,290);
     HelloTVXlet.Rood.setLocation(360,0);
     HelloTVXlet.Rood.setBackground(new DVBColor(HelloTVXlet.donkerRood));
     HelloTVXlet.Rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
     HelloTVXlet.scene.add(HelloTVXlet.Rood);
     HelloTVXlet.scene.popToFront(HelloTVXlet.Rood);
     
     HelloTVXlet.Geel = new HTextButton(" ");
     HelloTVXlet.Geel.setSize(360,290);
     HelloTVXlet.Geel.setLocation(0,290);
     HelloTVXlet.Geel.setBackground(new DVBColor(HelloTVXlet.donkerGeel));
     HelloTVXlet.Geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
     HelloTVXlet.scene.add(HelloTVXlet.Geel);
     HelloTVXlet.scene.popToFront(HelloTVXlet.Geel);
     
     HelloTVXlet.Blauw = new HTextButton(" ");
     HelloTVXlet.Blauw.setSize(360,290);
     HelloTVXlet.Blauw.setLocation(360,290);
     HelloTVXlet.Blauw.setBackground(new DVBColor(HelloTVXlet.donkerBlauw));
     HelloTVXlet.Blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
     HelloTVXlet.scene.add(HelloTVXlet.Blauw);
     HelloTVXlet.scene.popToFront(HelloTVXlet.Blauw);
     
     
     HelloTVXlet.scene.repaint();
     
    }
    
}
