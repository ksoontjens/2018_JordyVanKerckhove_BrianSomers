package hellotvxlet;
import javax.tv.xlet.*;
import org.havi.ui.*;
import org.dvb.ui.*;
import java.awt.*;


public class HelloTVXlet implements Xlet {

  private XletContext actueleXletContext;
  private HScene scene;
  private boolean debug = true;
  
  private HTextButton Knop1, Knop2, Knop3, Knop4;

    public void initXlet(XletContext context) throws XletStateChangeException {
        
     if (debug) System.out.println("Xlet initialiseren");
     this.actueleXletContext = context;
     
     HSceneTemplate sceneTemplate = new HSceneTemplate();
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
     scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
     
     Knop1 = new HTextButton("Knop1");
     Knop1.setSize(100,100);
     Knop1.setLocation(300,100);
     Knop1.setBackground(new DVBColor(0,0,0,179));
     Knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
       
     scene.add(Knop1);
     
     Knop2 = new HTextButton("Knop2");
     Knop2.setSize(100,100);
     Knop2.setLocation(500,250);
     Knop2.setBackground(new DVBColor(0,0,0,179));
     Knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
       
     scene.add(Knop2);
     
     Knop3 = new HTextButton("Knop3");
     Knop3.setSize(100,100);
     Knop3.setLocation(300,400);
     Knop3.setBackground(new DVBColor(0,0,0,179));
     Knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
       
     scene.add(Knop3);
     
     Knop4 = new HTextButton("Knop4");
     Knop4.setSize(100,100);
     Knop4.setLocation(100,250);
     Knop4.setBackground(new DVBColor(0,0,0,179));
     Knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
       
     scene.add(Knop4);

    }
    
    
    
    //vanaf hier grafische dingen en reageren op input gebruiker
    public void startXlet() throws XletStateChangeException {
       if (debug) System.out.println("Xlet Starten");
       
       scene.validate();
       scene.setVisible(true);
    }

    // vanaf hier enkel gebruiken bij meerdere Xlets
    public void pauseXlet() {
        
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException{
     
    }
}
