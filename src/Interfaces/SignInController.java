/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class SignInController implements Initializable {

    @FXML
    private TextField usernameu;
    @FXML
    private TextField mdpu;
    @FXML
    private Button btninscrire;
    @FXML
    private Button btnconn;

    public static String idurecup;
    public static String prenomurecup;
    public static String adresseurecup;
    public static String nomurecup;
    public static String datenaissurecup;
    public static String usernameurecup;
    public static String mailurecup;
    public static String mdpurecup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscrire(ActionEvent event) {
    }

    @FXML
    private void conn(ActionEvent event) {
        String lo = usernameu.getText();
        String psw = mdpu.getText();
        User e = new User();

        String req = "SELECT * FROM user WHERE `username`='" + lo + "' AND `password`='" + psw + "'";
        PreparedStatement stm;

        try {
            stm = ConnexionBD.getinstance().getcnx().prepareStatement(req);
            ResultSet rs = stm.executeQuery(req);
            if (rs.next() == true) {

                int est_admin = rs.getInt("status");
                SignInController.nomurecup = rs.getString("nom");
                SignInController.prenomurecup = rs.getString("prenom");
                SignInController.adresseurecup = rs.getString("addresse");
                SignInController.datenaissurecup = rs.getString("date_naissance");
                SignInController.usernameurecup = rs.getString("username");
                SignInController.mdpurecup = rs.getString("password");
                SignInController.mailurecup = rs.getString("email");
                
                if (est_admin
                        == 1) {

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                        Stage stage = (Stage) btnconn.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("UserAffiche.fxml"));
                        Stage stage = (Stage) btnconn.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Informations incorrectes");
                alert.setContentText("\"Vérifiez votre identifiant  et votre mot de passe\"!");
                alert.showAndWait();
                mdpu.clear();

            }

        } catch (SQLException ex) {

            Logger.getLogger(SignInController.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

}
