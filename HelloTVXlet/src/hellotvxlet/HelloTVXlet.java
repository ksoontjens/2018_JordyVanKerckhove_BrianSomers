package hellotvxlet;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import org.dvb.ui.*;
import java.awt.*;
import org.havi.ui.event.*;


public class HelloTVXlet implements Xlet, HActionListener {

  private XletContext actueleXletContext;
  private HScene scene;
  private boolean debug = true;
  private int command;
  private HTextButton Knop1, Knop2, Knop3, Knop4;

    public void initXlet(XletContext context) throws XletStateChangeException {
        
     if (debug) System.out.println("Xlet initialiseren");
     this.actueleXletContext = context;
     
     HSceneTemplate sceneTemplate = new HSceneTemplate();
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
     scene = HSceneFactory.getInstance().getBestScene(sceneTemplate); // 720*580
     
     // green dark: 54, 181, 9, 255
     // green light: 72, 249, 7, 255
     Knop1 = new HTextButton(" ");
     Knop1.setSize(360,290);
     Knop1.setLocation(0,0);
     Knop1.setBackground(new DVBColor(54, 181, 9, 255));
     Knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Knop1);
     
     // red dark : 209, 3, 0, 255
     // red light : 255, 22, 22, 255
     Knop2 = new HTextButton(" ");
     Knop2.setSize(360,290);
     Knop2.setLocation(360,0);
     Knop2.setBackground(new DVBColor(209, 3, 0, 255));
     Knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Knop2);
     
     // yellow dark: 242, 213, 0, 255
     // yellow light: 255, 234, 76, 255
     Knop3 = new HTextButton(" ");
     Knop3.setSize(360,285);
     Knop3.setLocation(0,290);
     Knop3.setBackground(new DVBColor(242, 213, 0, 255));
     Knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Knop3);
     
     //blue dark : 0, 132, 209, 255
     // blue light: 112, 202, 255, 255
     Knop4 = new HTextButton(" ");
     Knop4.setSize(360,285);
     Knop4.setLocation(360,290);
     Knop4.setBackground(new DVBColor(0, 132, 209, 255));
     Knop4.setBackgroundMode(HVisible.BACKGROUND_FILL); 
     scene.add(Knop4);

     //beweging pijltjes toetsen
     Knop1.setFocusTraversal(null, Knop3, null, Knop2);
     Knop2.setFocusTraversal(null, Knop4, Knop1, null);
     Knop3.setFocusTraversal(Knop1, null, null, Knop4);
     Knop4.setFocusTraversal(Knop2, null, Knop3, null);
     Knop1.requestFocus();
     
     // button events
     Knop1.setActionCommand("1");
     Knop2.setActionCommand("2");
     Knop3.setActionCommand("3");
     Knop4.setActionCommand("4");
     
     Knop1.addHActionListener(this);
     Knop2.addHActionListener(this);
     Knop3.addHActionListener(this);
     Knop4.addHActionListener(this);
     
    }

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

    public void actionPerformed(ActionEvent e) {
       command = Integer.parseInt(e.getActionCommand());
       switch(command)
       {
           case 1:
               System.out.println("1");
               break;
           case 2:
               System.out.println("2");
               break;
           case 3:
               System.out.println("3");
               break;
           case 4:
               System.out.println("4");
               break;
               
           default:
           break;
       }
    }
}
