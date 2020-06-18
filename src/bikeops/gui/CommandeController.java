/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class CommandeController implements Initializable {

    private Label lab;
    @FXML
    private Button btnId;
    @FXML
    private RadioButton btnCateId;
    @FXML
    private RadioButton btnlivId;
    @FXML
    private RadioButton btnMagId;
    @FXML
    private RadioButton btnDomId;
    
    final ToggleGroup groupPaiement = new ToggleGroup();
    final ToggleGroup groupLivraison = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCateId.setToggleGroup(groupPaiement);
        btnlivId.setToggleGroup(groupPaiement);
        btnMagId.setToggleGroup(groupLivraison);
        btnDomId.setToggleGroup(groupLivraison);
        
        
        
        
    }    


    @FXML
    private void confirmerCommande(ActionEvent event) throws IOException {
        Parent list = FXMLLoader.load(getClass().getResource("Commandefinal.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();

    }
    
}
