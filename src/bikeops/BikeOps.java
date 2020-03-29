/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;

import Services.ServicePanier;
import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class BikeOps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ConnexionBD conx = ConnexionBD.getinstance();
        ServicePanier sp = new ServicePanier();
        sp.afficherPanier();
  
    }
}
