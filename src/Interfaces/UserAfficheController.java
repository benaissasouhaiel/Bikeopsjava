/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Produit;
import Services.ServiceProduit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class UserAfficheController implements Initializable {

    
    @FXML
    private TableView<Produit> tablep;
    @FXML
    private TableColumn<Produit, Integer> idp;
    @FXML
    private TableColumn<Produit, Integer> qtep;
    @FXML
    private TableColumn<Produit, Integer> prixp;
    @FXML
    private TableColumn<Produit, String> nomp;
    @FXML
    private TableColumn<Produit, String> descp;

    private final ObservableList<Produit> datta = FXCollections.observableArrayList();
    public static String idprecup;
    public static String qteprecup;
    public static Float prixprecup;
    public static String nomprecup;
    public static String descprecup;
    @FXML
    private TextField rech;
    private void settable() {

        ServiceProduit sp = new ServiceProduit();

        ArrayList<Produit> u = (ArrayList<Produit>) sp.ListProduit();
        ObservableList<Produit> obs = FXCollections.observableArrayList(u);

        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomp.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixp.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("Prix"));
        qtep.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte"));
        descp.setCellValueFactory(new PropertyValueFactory<Produit, String>("desc"));

        datta.addAll(u);
        tablep.setItems(obs);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<Produit> filteredData = new FilteredList<>(datta, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(Produit.getId()).equals(lowerCaseFilter)) {
                    return true;
                } else if (Produit.getDesc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Produit.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablep.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablep.setItems(sortedData);
    }    

    
    
}
