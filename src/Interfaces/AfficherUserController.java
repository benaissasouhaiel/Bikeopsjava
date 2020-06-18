/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Produit;
import Entities.User;
import Services.ServiceProduit;
import Services.ServiceUser;
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
public class AfficherUserController implements Initializable {

    @FXML
    private TextField rech;
    @FXML
    private TableView<User> tableu;
    @FXML
    private TableColumn<User, Integer> idu;
    @FXML
    private TableColumn<User, String> nomu;
    @FXML
    private TableColumn<User, String> prenomu;
    @FXML
    private TableColumn<User, String> adresseu;
    @FXML
    private TableColumn<User, String> datenaissu;
    @FXML
    private TableColumn<User, String> usernameu;
    @FXML
    private TableColumn<User, String> mailu;
    @FXML
    private TableColumn<User, String> mdpu;
    @FXML
    private Button btnret;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmod;

    private final ObservableList<User> datta = FXCollections.observableArrayList();
    public static String idurecup;
    public static String prenomurecup;
    public static String adresseurecup;
    public static String nomurecup;
    public static String datenaissurecup;
    public static String usernameurecup;
    public static String mailurecup;
    public static String mdpurecup;

    private void settable() {

        ServiceUser su = new ServiceUser();

        ArrayList<User> u = (ArrayList<User>) su.ListUser();
        ObservableList<User> obs = FXCollections.observableArrayList(u);

        idu.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomu.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenomu.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        adresseu.setCellValueFactory(new PropertyValueFactory<User, String>("addresse"));
        datenaissu.setCellValueFactory(new PropertyValueFactory<User, String>("date_naissance"));
        usernameu.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        mailu.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        mdpu.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        datta.addAll(u);
        tableu.setItems(obs);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<User> filteredData = new FilteredList<>(datta, b -> true);

        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(User -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(User.getId()).equals(lowerCaseFilter)) {
                    return true;
                } else if (User.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (User.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches last name.
                } else {

                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableu.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableu.setItems(sortedData);
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
        JLabel label1 = new JLabel("Delete Utilisateur");
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
            User u = tableu.getSelectionModel().getSelectedItem();

            ServiceUser su = new ServiceUser();
            su.supprimerUser(u.getId());
            System.out.println("Utilisateur deleted");
            Notifications.create().title("Supression").text("Utilisateur supprimé").showConfirm();
        } else if (res == 1) {
            System.out.println("Pressed No");

        } else {
            System.out.println("Pressed CANCEL");
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            User u = tableu.getSelectionModel().getSelectedItem();

            AfficherUserController.idurecup = "" + u.getId();
            AfficherUserController.prenomurecup =u.getPrenom();
            AfficherUserController.adresseurecup =u.getAddress();
            AfficherUserController.nomurecup =u.getNom();
            AfficherUserController.datenaissurecup = "" + u.getDate_naiss();
            AfficherUserController.usernameurecup =u.getUsername();
            AfficherUserController.mailurecup =u.getMail();
            AfficherUserController.mdpurecup =u.getMdp();

            Parent root = FXMLLoader.load(getClass().getResource("DetailsUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

