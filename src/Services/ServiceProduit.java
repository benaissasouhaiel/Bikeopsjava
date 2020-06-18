/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author Souhaiel
 */
public class ServiceProduit {

    Connection conx;

    public ServiceProduit() {
        conx = ConnexionBD.getinstance().getcnx();
    }

    public void addProduit(Produit p) {
        try {
            String requete = "insert into produit (id,stock,prix,name,description,categorie_id) values(?,?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.setInt(2, p.getQte());
            pst.setInt(3, p.getPrix());
            pst.setString(4, p.getName());
            pst.setString(5, p.getDesc());
            pst.setInt(6, p.getCat().getId());
            pst.executeUpdate();
            System.out.println("produit ajouté !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Produit> ListProduit() {
        List<Produit> Mylist = new ArrayList<>();
        try {
            String requete = "select * from produit";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setQte(rs.getInt("stock"));
                p.setPrix(rs.getInt("prix"));
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDesc(rs.getString("description"));

                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateProduit(Produit p) {
        try {
            String requete = "update produit set id=?,qte=?,prix=?,name=?,desc=? "
                    + "where id = ?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, p.getQte());
            pst.setFloat(2, p.getPrix());
            pst.setInt(5, p.getId());
            pst.setString(3, p.getName());
            pst.setString(4, p.getDesc());
            pst.executeUpdate();
            System.out.println("Produit mis a jour !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerProduit(int Id) {
        try {
            PreparedStatement pt = conx.prepareStatement("delete from Produit where Id=?");
            pt.setInt(1, Id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("Produit supprimé !!");
        }
    }
}
