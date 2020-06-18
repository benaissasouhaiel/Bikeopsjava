/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops.gui;

import Entities.Coupon;
import Entities.Panier;
import Entities.PanierDetail;
import Services.ServiceCoupon;
import Services.ServicePanier;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class PanierController implements Initializable {

    
   
    @FXML
    private TableView<PanierDetail> table;
    @FXML
    private TableColumn<PanierDetail, Integer> quantiteP;
   ServicePanier p = new ServicePanier();
    ObservableList<PanierDetail> datalist;
    @FXML
    private Button btnId;
    @FXML
    private TableColumn<PanierDetail, Integer> PrixU;
    @FXML
    private TextField coupon;
    @FXML
    private TextField total;
    @FXML
    private TableColumn<PanierDetail, Integer> soustotal;
    @FXML
    private TableColumn<PanierDetail, String> nomP;
    @FXML
    private Button btn1Id;
    @FXML
    private TextField totalreduced;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // static user
        int user_id = 1;
         int sum = 0;
        ArrayList<PanierDetail> paniers = (ArrayList<PanierDetail>) p.displayAll(user_id);
        paniers.forEach(e->{
            e.setTotal(e.getPrix()*e.getQuantite());
        });
        
        for(int i=0; i<paniers.size(); i++) {
            sum += (paniers.get(i).getTotal());
        }

        total.setText(String.valueOf(sum));
        datalist = FXCollections.observableArrayList(paniers);
        nomP.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        PrixU.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        soustotal.setCellValueFactory(new PropertyValueFactory<>("total"));
       // soustotal.setCellValueFactory();
        // TableColumn<PanierDetail, Integer> setCellValueFactory = prixT.setCellValueFactory(new PropertyValueFactory<>(p.panierTotal(table.getItems(prixU),table.getItems(quantiteP))));
        // addButtonToTable();
        table.setItems(datalist);
  }    
 

    @FXML
    private void applyCoupon(ActionEvent event) {
        
        ServiceCoupon serviceCoupon = new ServiceCoupon();
        ServicePanier servicePanier = new ServicePanier();
        Coupon theCoupon = serviceCoupon.getCouponByCode(coupon.getText());
        // we acctually have a coupon back from the service
        if(theCoupon.getCode() != null) {
             int reduction = (int) ((Integer.parseInt(total.getText())) * (theCoupon.getPourcentage() / 100));
             int totalReduced = Integer.parseInt(total.getText()) - reduction;
             totalreduced.setText(String.valueOf(totalReduced));
             //updtae the total of the cart
             // for now we use USER with id = 1, later on change it to connected user.
             Panier panier = servicePanier.getPanierByUser(1);
             servicePanier.modifierPanier(panier.getId(), totalReduced);
        } else {
            // display error 
             coupon.setText("Code Expiré");
        }
    
    }

    @FXML
    private void ToPreCommande(ActionEvent event) throws IOException {
        
          Parent list = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();

        
    }
        
        
    
    
}
