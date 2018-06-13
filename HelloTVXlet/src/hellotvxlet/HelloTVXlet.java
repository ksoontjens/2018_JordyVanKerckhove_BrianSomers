package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.event.HActionListener;
import org.havi.ui.*;
import java.util.Timer;

public class HelloTVXlet implements Xlet, HActionListener {
          
  private static XletContext actueleXletContext;
  public static HScene scene;
  private boolean debug = true;
  private int command;
  public static HTextButton Groen, Rood, Geel, Blauw;
  
  public static DVBColor lichtGroen = new DVBColor(72, 249, 7, 255); 
  public static DVBColor donkerGroen = new DVBColor(54, 181, 9, 255);
  
  public static DVBColor lichtRood = new DVBColor(255, 22, 22, 255);
  public static DVBColor donkerRood = new DVBColor(209, 3, 0, 255);
  
  public static DVBColor lichtGeel = new DVBColor(255, 234, 76, 255);
  public static DVBColor donkerGeel = new DVBColor(242, 213, 0, 255);
  
  public static DVBColor lichtBlauw= new DVBColor(112, 202, 255, 255);
  public static DVBColor donkerBlauw = new DVBColor(0, 132, 209, 255);

    public void initXlet(XletContext context) throws XletStateChangeException {
        
     if (debug) System.out.println("Xlet initialiseren");
     this.actueleXletContext = context;
     
     HSceneTemplate sceneTemplate = new HSceneTemplate();
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
     sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
     scene = HSceneFactory.getInstance().getBestScene(sceneTemplate); // 720*580
     
     Groen = new HTextButton(" ");
     Groen.setSize(360,290);
     Groen.setLocation(0,0);
     Groen.setBackground(new DVBColor(54, 181, 9, 255));
     Groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Groen);
     
     Rood = new HTextButton(" ");
     Rood.setSize(360,290);
     Rood.setLocation(360,0);
     Rood.setBackground(new DVBColor(209, 3, 0, 255));
     Rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Rood);
     
     Geel = new HTextButton(" ");
     Geel.setSize(360,285);
     Geel.setLocation(0,290);
     Geel.setBackground(new DVBColor(242, 213, 0, 255));
     Geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Geel);
     
     Blauw = new HTextButton(" ");
     Blauw.setSize(360,285);
     Blauw.setLocation(360,290);
     Blauw.setBackground(new DVBColor(0, 132, 209, 255));
     Blauw.setBackgroundMode(HVisible.BACKGROUND_FILL); 
     scene.add(Blauw);

     //beweging pijltjes toetsen
     Groen.setFocusTraversal(null, Geel, null, Rood);
     Rood.setFocusTraversal(null, Blauw, Groen, null);
     Geel.setFocusTraversal(Groen, null, null, Blauw);
     Blauw.setFocusTraversal(Rood, null, Geel, null);
     Groen.requestFocus();
     
     // button events
     Groen.setActionCommand("1");
     Rood.setActionCommand("2");
     Geel.setActionCommand("3");
     Blauw.setActionCommand("4");
     
     Groen.addHActionListener(this);
     Rood.addHActionListener(this);
     Geel.addHActionListener(this);
     Blauw.addHActionListener(this);
     
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
    public void WachtSecond()
    {
        for(int i =0; i<50000;i++)
        {System.out.println(i);}
    }
    
    public void GroenSecondlicht()
    {
     Groen = new HTextButton(" ");
     Groen.setSize(360,290);
     Groen.setLocation(0,0);
     Groen.setBackground(new DVBColor(lichtGroen));
     Groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Groen);
     scene.popToFront(Groen);
    }
    
    public void RoodSecondLicht()
    {
     Rood = new HTextButton(" ");
     Rood.setSize(360,290);
     Rood.setLocation(360,0);
     Rood.setBackground(new DVBColor(lichtRood));
     Rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Rood);
     scene.popToFront(Rood);
    }
    
    public void GeelSecondLicht()
    {
     Geel = new HTextButton(" ");
     Geel.setSize(360,290);
     Geel.setLocation(0,290);
     Geel.setBackground(new DVBColor(lichtGeel));
     Geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Geel);
     scene.popToFront(Geel);
    }
    
    public void BlauwSecondLicht()
    {
     Blauw = new HTextButton(" ");
     Blauw.setSize(360,290);
     Blauw.setLocation(360,290);
     Blauw.setBackground(new DVBColor(lichtBlauw));
     Blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
     scene.add(Blauw);
     scene.popToFront(Blauw);
    }
    
    
    public void actionPerformed(ActionEvent e) {
    
       command = Integer.parseInt(e.getActionCommand());
       MijnTimerTask objMijnTimerTask = new MijnTimerTask();
       Timer timer = new Timer();
       
       switch(command)
       {
          
           case 1:
               System.out.println("Groen");   
               GroenSecondlicht();
               timer.schedule(objMijnTimerTask, 500);
              
               break;
           case 2:
               System.out.println("Rood");
               RoodSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
           case 3:
               System.out.println("Geel");
               GeelSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
           case 4:
               System.out.println("Blauw");
               BlauwSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
               
           default:
           break;
       }
       scene.repaint();
    }
}
