/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import java.sql.Connection;
import java.sql.Date;
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

    public void ajouterCommande(Commande c) {
        Statement st;
        try {
            String req = " INSERT INTO `commande`( `date`, `etat`,`typePaiment`,`panier_id`,`livraison_id`) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(req);
          // pst.setInt(1 ,c.getId());

            pst.setDate(1, Date.valueOf(c.getDate()));
            pst.setString(2, c.getEtat());
           pst.setString(3, c.getTypePaiment());
           pst.setInt(4, c.getPanier_id());
           pst.setInt(5,c.getLivraison_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);        }

    }

    public void afficherCommande() {
        PreparedStatement pt;
        try {

            pt = conx.prepareStatement("SELECT * FROM `Commande`");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("commande [id :" + rs.getInt(1) + ",etat:" + rs.getString(2) + ",Date:" + rs.getString(3) + ",TypePaiment:" + rs.getString(4) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierCommande(String etat, String Date,String TypePaiment,  int id) {
        try {
            PreparedStatement pt = conx.prepareStatement("UPDATE `commande` SET `etat`=? ,`date`=? ,`TypePaiment`=? where Id=?");

            pt.setString(1, etat);
            pt.setString(2, Date);
            pt.setString(3, TypePaiment);
            pt.setInt(4, id);
           

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerCommande(int id) {
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
            String date = rs.getString(3);
            String TypePaiment = rs.getString(4);

           
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
            String date = rs.getString(3);
            String TypePaiment = rs.getString(4);
           
            Commande c = new Commande(date, etat);
            commande.add(c);
        }
        return commande;
    }
}


