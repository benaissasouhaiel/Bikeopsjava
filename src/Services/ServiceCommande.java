/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class ServiceCommande {

    Connection conx = ConnexionBD.getinstance().getcnx();

    public void ajouterOrder(Commande c) {
        Statement st;
        try {
            st = conx.createStatement();
            String req = " INSERT INTO `commande`( `id`,`etat`, `Date`) VALUES(" + c.getId() + ",'" + c.getEtat() + "','" + c.getDate() + "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherCommande() {
        PreparedStatement pt;
        try {

            pt = conx.prepareStatement("SELECT * FROM `Commande`");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("commande [id :" + rs.getInt(1) + ",etat:" + rs.getString(2) + ",Date:" + rs.getString(3) + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierOrder(String etat, String Date, int id) {
        try {
            PreparedStatement pt = conx.prepareStatement("UPDATE `commande` SET `etat`=? ,`date`=? where Id=?");

            pt.setString(1, etat);
            pt.setString(2, Date);
            pt.setInt(3, id);

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerOrder(int id) {
        PreparedStatement pt;
        try {
            pt = conx.prepareStatement("DELETE FROM `commande` WHERE Id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Commande> afficherCommandeConf() throws SQLException {
        List<Commande> commande = new ArrayList<>();
        Statement st;
        st = conx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `commande` WHERE `etat`=confirmée");
        while (rs.next()) {
            int id =rs.getInt(1);
            String etat = rs.getString(2);
            String date = rs.getDate(3).toString();

           
            Commande c = new Commande(date, etat);
            commande.add(c);
        }
        return commande;
    }
       public List<Commande> afficherCommandeEnAttente() throws SQLException {
        List<Commande> commande = new ArrayList<>();
        Statement st;
        st = conx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `commande` WHERE `etat`='en_attente'");
        while (rs.next()) {
            int id =rs.getInt(1);
            String etat = rs.getString(2);
            String date = rs.getDate(3).toString();

           
            Commande c = new Commande(date, etat);
            commande.add(c);
        }
        return commande;
    }
}


