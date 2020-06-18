/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnajoutp;
    @FXML
    private Button btnajoutc;
    @FXML
    private Button btnaffp;
    @FXML
    private Button btnaffc;
    @FXML
    private Button btnajoutu;
    @FXML
    private Button btnaffu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) {
        try {
                        Parent root = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
                        Stage stage = (Stage) btnajoutp.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void ajoutc(ActionEvent event) {
        try {
                        Parent root = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
                        Stage stage = (Stage) btnajoutc.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void affp(ActionEvent event) {
        try {
                        Parent root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
                        Stage stage = (Stage) btnaffp.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void affc(ActionEvent event) {
        try {
                        Parent root = FXMLLoader.load(getClass().getResource("AfficherCategorie.fxml"));
                        Stage stage = (Stage) btnaffc.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void ajoutu(ActionEvent event) {
         try {
                        Parent root = FXMLLoader.load(getClass().getResource("AjoutUser.fxml"));
                        Stage stage = (Stage) btnaffc.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void affu(ActionEvent event) {
         try {
                        Parent root = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));
                        Stage stage = (Stage) btnaffc.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
