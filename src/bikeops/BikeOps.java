/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;

import Entities.Commande;

import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;

import Services.ServiceLivreur;
import Services.ServiceLivraison;
import Entities.Livreur;
import Entities.Livraison;
import Services.ServiceProduit;
import Entities.Panier;
import Entities.User;
import Services.ServiceCommande;
import Services.ServiceCoupon;
import Services.ServiceUser;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class BikeOps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ConnexionBD conx = ConnexionBD.getinstance();
        
        Calendar c = Calendar.getInstance();
        Date dd = new Date();
        c.set(2019, 3, 16);
        dd = c.getTime();
        User u = new User(666, "souhaiel", "souhaiel", "souhaiel", "souhaiel", "souhaiel", "souhaiel",dd);
        ServiceUser su = new ServiceUser();
        su.UpdateUser(u);
    }
}
