/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops.gui;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;  
import sun.audio.*;
import java.io.*;


/**
 *
 * @author laoui
 */
public class BikeMain extends Application {
    
 
    @Override
    public void start(Stage stage) throws Exception {
        music();
        Parent root = FXMLLoader.load(getClass().getResource("panier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }  
        public static void music() 
   {       
       AudioPlayer MGP = AudioPlayer.player;
       AudioStream BGM;
       AudioData MD;

       ContinuousAudioDataStream loop = null;

       try
       {
           InputStream test = new FileInputStream("C:\\a.wav");
           BGM = new AudioStream(test);
           AudioPlayer.player.start(BGM);
       }
       catch(FileNotFoundException e){
           System.out.print(e.toString());
       }
       catch(IOException error)
       {
           System.out.print(error.toString());
       }
       MGP.start(loop);
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
