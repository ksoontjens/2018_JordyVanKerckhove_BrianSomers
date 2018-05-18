/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import org.dvb.ui.*;
import java.awt.*;
import org.havi.ui.event.*;

/**
 *
 * @author student
 */
public class MainMenu {
private Stage primaryStage;


@Override
public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
   
}

public void changeScene(String fxml){
    Parent pane = FXMLLoader.load(
           getClass().getResource(fxml));

   Scene scene = new Scene( pane );
   primaryStage.setScene(scene);
}
private Stage primaryStage;

@Override
public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    ...
}

public void changeScene(String fxml){
    Parent pane = FXMLLoader.load(
           getClass().getResource(fxml));

   Scene scene = new Scene( pane );
   primaryStage.setScene(scene);
}
}
