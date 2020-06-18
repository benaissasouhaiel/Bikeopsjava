/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author Souhaiel
 */
public class ServiceUser {

    Connection conx;

    public ServiceUser() {
        conx = ConnexionBD.getinstance().getcnx();
    }

    public void addUser(User u) {
        try {
            String requete = "insert into utilisateur (id,username,email,password,nom,prenom,addresse,date_naissance) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getUsername());
            pst.setString(3, u.getMail());
            pst.setString(4, u.getMdp());
            pst.setString(5, u.getNom());
            pst.setString(6, u.getPrenom());
            pst.setString(7, u.getAddress());
            pst.setDate(8, new java.sql.Date(u.getDate_naiss().getTime()));

            pst.executeUpdate();
            System.out.println("Utilisateur ajouté !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> ListUser() {
        List<User> Mylist = new ArrayList<>();
        try {
            String requete = "select * from utilisateur";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setMail(rs.getString("email"));
                u.setMdp(rs.getString("password"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setAddress(rs.getString("addresse"));
                u.setDate_naiss(rs.getDate("date_naissance"));
                Mylist.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateUser(User u) {
        try {
            String requete = "update utilisateur set username=?,email=?,password=?,nom=?,prenom=?,addresse=? "
                    + "where id = ?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(7, u.getId());
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getMail());
            pst.setString(3, u.getMdp());
            pst.setString(4, u.getNom());
            pst.setString(5, u.getPrenom());
            pst.setString(6, u.getAddress());

            pst.executeUpdate();
            System.out.println("Utilisateur mis a jour !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerUser(int Id) {
        try {
            PreparedStatement pt = conx.prepareStatement("delete from utilisateur where Id=?");
            pt.setInt(1, Id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("Utilisateur supprimé !!");
        }
    }
}
