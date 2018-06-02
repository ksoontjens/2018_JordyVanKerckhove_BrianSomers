/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;



/**
 *
 * @author student
 */
public class MainMenu implements Xlet, HActionListener{






public void changeScene(){
    

  
}

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
        
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
         HScene scene;
   HSceneTemplate template = new  HSceneTemplate();
   scene = HSceneFactory.getInstance().getBestScene(template);
   
   HTextButton btn = new HTextButton("Start");
   btn.setActionCommand("1");
   
   scene.add(btn);
   
   btn = new HTextButton("Stop");
   scene.add(btn);
    }

    public void pauseXlet() {
       
    }

    public void startXlet() throws XletStateChangeException {
        
        ArrayList arr = new ArrayList();
        arr.add("2");
        int i = Integer.parseInt((String) arr.get(0));
        
    }

    public void actionPerformed(ActionEvent e) {
        int command = Integer.parseInt(e.getActionCommand());
       switch(command){
           case 1:
               break;
           default:
               break;
           
       }
       
    }



}
