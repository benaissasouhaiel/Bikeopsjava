/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Categorie;
import Services.ServiceCategorie;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<Categorie> tablec;
    @FXML
    private TableColumn<Categorie, Integer> idc;
    @FXML
    private TableColumn<Categorie, String> namec;
    @FXML
    private TableColumn<Categorie, String> descc;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnret;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmod;

    private final ObservableList<Categorie> datta = FXCollections.observableArrayList();
    public static Integer idcrecup;
    public static String namecrecup;
    public static String desccrecup;

    private void settable() {

        ServiceCategorie sc = new ServiceCategorie();

        ArrayList<Categorie> c = (ArrayList<Categorie>) sc.ListCategorie();
        ObservableList<Categorie> obs = FXCollections.observableArrayList(c);
        tablec.setItems(obs);
        idc.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
        namec.setCellValueFactory(new PropertyValueFactory<Categorie, String>("name"));
        descc.setCellValueFactory(new PropertyValueFactory<Categorie, String>("description"));

        datta.addAll(c);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<Categorie> filteredData = new FilteredList<>(datta, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Categorie -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Categorie.getDesc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Categorie.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Categorie> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablec.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablec.setItems(sortedData);
        // TODO
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
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
    private void supprimer(ActionEvent event) {
        ImageIcon icon = new ImageIcon("E −\\new.PNG");
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(250, 100));
        panel.setLayout(null);
        JLabel label1 = new JLabel("Delete Categorie");
        label1.setVerticalAlignment(SwingConstants.BOTTOM);
        label1.setBounds(20, 20, 200, 30);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        JLabel label2 = new JLabel("Do you still want to Delete it?");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(20, 80, 200, 20);
        panel.add(label2);
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        int res = JOptionPane.showConfirmDialog(null, panel, "File",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        if (res == 0) {
            Categorie c = tablec.getSelectionModel().getSelectedItem();

            ServiceCategorie sc = new ServiceCategorie();
            sc.supprimerCategorie(c.getId());
            System.out.println("Categorie deleted");
            Notifications.create().title("Supression").text("Categorie supprimé").showConfirm();
        } else if (res == 1) {
            System.out.println("Pressed No");

        } else {
            System.out.println("Pressed CANCEL");
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {

        Categorie c = tablec.getSelectionModel().getSelectedItem();

        AfficherCategorieController.namecrecup = c.getName();
        AfficherCategorieController.desccrecup = c.getDesc();

        Parent root = FXMLLoader.load(getClass().getResource("editevent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}
