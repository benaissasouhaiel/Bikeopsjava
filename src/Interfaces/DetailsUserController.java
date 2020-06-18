/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class DetailsUserController implements Initializable {

    @FXML
    private TextField tfiddu;
    @FXML
    private TextField tfusernamedu;
    @FXML
    private TextField tfmaildu;
    @FXML
    private TextField tfmdpdu;
    @FXML
    private Button btnret;
    
    @FXML
    private DatePicker datep;
    @FXML
    private TextField tfnomu;
    @FXML
    private TextField tfprenomu;
    @FXML
    private TextField tfadresseu;
    @FXML
    private Button btnmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextID(String message) {
        this.tfiddu.setText(message);
    }

    public void setTextUsername(String message) {
        this.tfusernamedu.setText(message);
    }

    public void setTextMail(String message) {
        this.tfmaildu.setText(message);
    }

    public void setTextMdp(String message) {
        this.tfmdpdu.setText(message);
    }

    public void setDate(LocalDate date) {

        
        datep.setValue(date);
    }
    public void setTextNom(String message) {
        this.tfnomu.setText(message);
    }
    public void setTextAdresse(String message) {
        this.tfadresseu.setText(message);
    }
    public void setTextPrenom(String message) {
        this.tfprenomu.setText(message);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        String Id = tfiddu.getText();
        String Username = tfusernamedu.getText();
        String Mail = tfmaildu.getText();
        String Motdepasse = tfmdpdu.getText();
        String Nom = tfnomu.getText();
        String Prenom = tfprenomu.getText();
        String Adresse = tfadresseu.getText();
        
        
        LocalDate D_event = datep.getValue();
        java.sql.Date daten = java.sql.Date.valueOf(D_event);

        LocalDate a = LocalDate.now();			// date courante
        java.sql.Date datesys = java.sql.Date.valueOf(a);
        if (datesys.compareTo(daten) > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date");
            alert.setContentText("Verifier votre date!");
            alert.showAndWait();
        } else {
            User u = new User(1, Username, Mail, Id, Nom, Prenom, Adresse, datesys);

            ServiceUser su = new ServiceUser();
            su.UpdateUser(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification User");
            alert.setContentText("Utilisateur a ete modifier avec succes!");

            alert.showAndWait();
        }
    }

}

   
   
   
    