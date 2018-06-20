package hellotvxlet;

import java.util.Random;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.event.HActionListener;
import org.havi.ui.*;
import java.util.Timer;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.event.HRcEvent;


public class HelloTVXlet implements Xlet, HActionListener, UserEventListener {
          
  private static XletContext actueleXletContext;
  public static HScene scene;
  private boolean debug = true;
  private int command;
  public static HTextButton Groen, Rood, Geel, Blauw, Start,  Quit, HighScore, HowToPlay, Background, SimonSays;
  private int scoreCounter = 1;
  public int finalscore =0;
  public static int[] userArr = new int[100];
  public static int[] comArr= new int[100];
  private boolean isEqual = true;
  private int buttonPressed=0;
  public static Object LOCK = new Object();
  
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
     
     SimonSays = new HTextButton ("Simon Says");
     SimonSays.setSize(380,120);
     SimonSays.setLocation(170,30);
     SimonSays.setBackground(donkerBlauw);
     SimonSays.setBackgroundMode((HVisible.BACKGROUND_FILL));
     scene.add(SimonSays);
     
     Start = new HTextButton ("Start");
     Start.setSize(360,100);
     Start.setLocation(180,170);
     Start.setBackground(donkerGroen);
     Start.setBackgroundMode((HVisible.BACKGROUND_FILL));
     scene.add(Start);
     Start.requestFocus();
     
     
     Quit = new HTextButton ("Quit");
     Quit.setSize(360,100);
     Quit.setLocation(180,280);
     Quit.setBackground(donkerRood);
     Quit.setBackgroundMode((HVisible.BACKGROUND_FILL));
     scene.add(Quit);
     
     HowToPlay = new HTextButton ("Arrowkeys = move || Enter = select");
     HowToPlay.setSize(500,100);
     HowToPlay.setLocation(100,400);
     HowToPlay.setBackground(donkerGeel);
     HowToPlay.setBackgroundMode((HVisible.BACKGROUND_FILL));
     scene.add(HowToPlay);
     
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
     
    Background= new HTextButton ("");
    Background.setSize(720,580);
    Background.setLocation(0,0);
    Background.setBackground(lichtBlauw);
    Background.setBackgroundMode(HVisible.BACKGROUND_FILL);
    scene.add(Background);
     
     //beweging pijltjes toetsen
     Groen.setFocusTraversal(null, Geel, null, Rood);
     Rood.setFocusTraversal(null, Blauw, Groen, null);
     Geel.setFocusTraversal(Groen, null, null, Blauw);
     Blauw.setFocusTraversal(Rood, null, Geel, null);
     Start.setFocusTraversal(null,Quit,null,null);
     Quit.setFocusTraversal(Start, null, null, null);
     Groen.requestFocus();

     // button events
     Start.setActionCommand("0");
     Groen.setActionCommand("1");
     Rood.setActionCommand("2");
     Geel.setActionCommand("3");
     Blauw.setActionCommand("4");
     Quit.setActionCommand("5");

     Groen.addHActionListener(this);
     Rood.addHActionListener(this);
     Geel.addHActionListener(this);
     Blauw.addHActionListener(this);

     Start.addHActionListener(this);
     Quit.addHActionListener(this);
    }

    public void startXlet() throws XletStateChangeException 
    {
         if (debug) System.out.println("Xlet Starten");
         scene.validate();
         scene.setVisible(true);
         setBtnsInactive();
         
         EventManager em = EventManager.getInstance();
         UserEventRepository eur = new UserEventRepository("KeyPresses");
         eur.addAllColourKeys();
         
         em.addUserEventListener(this, eur);
       
         Game();
         
    }

    public void pauseXlet() {
        
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException{
     
    }
    
    public static void GroenSecondlicht()
    {
         Groen = new HTextButton(" ");
         Groen.setSize(360,290);
         Groen.setLocation(0,0);
         Groen.setBackground(new DVBColor(lichtGroen));
         Groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
         scene.add(Groen);
         scene.popToFront(Groen);
    }
    
    public static void RoodSecondLicht()
    {
         Rood = new HTextButton(" ");
         Rood.setSize(360,290);
         Rood.setLocation(360,0);
         Rood.setBackground(new DVBColor(lichtRood));
         Rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
         scene.add(Rood);
         scene.popToFront(Rood);
    }
    
    public static void GeelSecondLicht()
    {
         Geel = new HTextButton(" ");
         Geel.setSize(360,290);
         Geel.setLocation(0,290);
         Geel.setBackground(new DVBColor(lichtGeel));
         Geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
         scene.add(Geel);
         scene.popToFront(Geel);
    }
    
    public static void BlauwSecondLicht()
    {
         Blauw = new HTextButton(" ");
         Blauw.setSize(360,290);
         Blauw.setLocation(360,290);
         Blauw.setBackground(new DVBColor(lichtBlauw));
         Blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
         scene.add(Blauw);
         scene.popToFront(Blauw);
    }
    
    public void setBtnsInactive()
    {
        Groen.setVisible(false);
        Geel.setVisible(false);
        Rood.setVisible(false);
        Blauw.setVisible(false);
        Quit.setVisible(true);
        Start.setVisible(true);
        HowToPlay.setVisible(true);
        Start.requestFocus();
    }
    
    public void setBtnsActive ()
    {
        Groen.setVisible(true);
        Geel.setVisible(true);
        Rood.setVisible(true);
        Blauw.setVisible(true);
        Start.setVisible(false);
        Quit.setVisible(false);
        HowToPlay.setVisible(false);
        SimonSays.setVisible(false);
        Groen.requestFocus();
    }
    
    public void GameOver()
    {
        Groen.setVisible(false);
        Geel.setVisible(false);
        Rood.setVisible(false);
        Blauw.setVisible(false);
        Quit.setVisible(true);
        
        HighScore = new HTextButton("Score: " + finalscore);
        HighScore.setSize(360,150);
        HighScore.setLocation(180,50);
        HighScore.setBackground(donkerGroen);
        HighScore.setBackgroundMode((HVisible.BACKGROUND_FILL));
        scene.add(HighScore);
        
        scene.popToFront(Quit);
        scene.popToFront(HighScore);
        Quit.requestFocus();
    }
  
    public void Game()
    {
        
        Random rnd = new Random();
         int n = 0; 

         // Vult computer array met 100 random nummers
         
        synchronized(this){try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }}
            for(int i=0; i < comArr.length ; i++)
            {
                n = rnd.nextInt(4) +1;
                comArr[i]= n;
            }

            while(isEqual == true)
            {
                System.out.println("repeat what simon says");
             //tonen computer sequence

                    for(int i=0; i<scoreCounter; i++)
                    {      
                    MijnTimerTask objMijnTimerTask = new MijnTimerTask();
                    Timer timer = new Timer();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(comArr[i]==1)
                    {
                        System.out.println("Groen");
                        GroenSecondlicht();
                        timer.schedule(objMijnTimerTask, 1000);
                        scene.repaint();
                    }
                    if(comArr[i]==2)
                    {
                        System.out.println("Rood");
                        RoodSecondLicht();
                        timer.schedule(objMijnTimerTask, 1000);
                        scene.repaint();
                    }
                    if(HelloTVXlet.comArr[i]==3)
                    {    
                        System.out.println("Geel");
                        GeelSecondLicht();
                        timer.schedule(objMijnTimerTask, 1000);
                        scene.repaint();
                    }
                    if(HelloTVXlet.comArr[i]==4)
                    {
                        System.out.println("Blauw");
                        BlauwSecondLicht();
                        timer.schedule(objMijnTimerTask, 1000);
                        scene.repaint();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
                //evenveel buttons opvragen als computer sequence
                for(int i=0;  i < scoreCounter; i++)
                {
                       
                    System.out.println("waiting for a button");
                    synchronized(this){try {
                            wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }}
                    userArr[i] = buttonPressed;
                    if(comArr[i] != userArr[i])
                    {
                        isEqual = false;
                        System.out.println("wrong button");
                        try {
                            Thread.sleep(1050);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        GameOver();
                        break;
                    }
                }
                finalscore=scoreCounter++;
            
            }  
        }
 
    
    public void actionPerformed(ActionEvent e) {
    
       command = Integer.parseInt(e.getActionCommand());
       MijnTimerTask objMijnTimerTask = new MijnTimerTask();
       Timer timer = new Timer();
       
       switch(command)
       {
           case 0:
               buttonPressed = 0;
               setBtnsActive();
               System.out.println("start pressed");
               break;
                            
            case 1:   
               buttonPressed = 1;
               System.out.println("Groen");   
               GroenSecondlicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
               
           case 2:
               buttonPressed  = 2;
               System.out.println("Rood");
               RoodSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
               
           case 3:
               buttonPressed  = 3;
               System.out.println("Geel");
               GeelSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
               
           case 4:
               buttonPressed  = 4;
               System.out.println("Blauw");
               BlauwSecondLicht();
               timer.schedule(objMijnTimerTask, 500);
               break;
 
           case 5:
               buttonPressed = 6;
               System.exit(0);
               break;
               
           default:
           break;
       }  
        synchronized(this){notify();} 
     
    }

    public void userEventReceived(UserEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
