/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
import Entities.PanierDetail;
import Entities.User;
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
 * @author laoui
 */
public class ServicePanier {
    
    
    Connection conx = ConnexionBD.getinstance().getcnx();
    
    
    
    
   
    public void ajouterPanier(Panier sc) throws SQLException
    {
        try{
    PreparedStatement pt = conx.prepareStatement(" insert into Panier ( total,utilisateur_id) values ( ?, ?)");
            
            pt.setInt(1,sc.getTotal());
            pt.setInt(2,sc.getUser_id());
                       
            
            pt.execute();
        }catch (SQLException ex)
            {
                System.out.println(ex);
        }
    }
    
    
    
    
      public void afficherPanier()
    {
        try {
            PreparedStatement pt =conx.prepareStatement("select * from Panier");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Panier {Lineitems:"+rs.getInt(2)+" ,Total:"+rs.getInt(3)+"");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void modifierPanier (int Id, int total)
    {
        try {
            PreparedStatement pt= conx.prepareStatement("update Pnaier set  total=?, where Id_Panier=?");
       
            pt.setInt(1,total);
            pt.setInt(2,Id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
}
       
       
    public void supprimerPanier(int Id)
    {
        try {
            PreparedStatement pt =conx.prepareStatement("delete from Panier where Id=?" );
            pt.setInt(1,Id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
      public List<PanierDetail> displayAll(int user_id) {
        ArrayList <PanierDetail> tab = new ArrayList ();     
         
         try {
             PreparedStatement pt =conx.prepareStatement(" SELECT p.name, p.prix, pc.quantite, pa.total from produit p INNER join "
                     + "commande_produit pc INNER join panier pa "
                     + "INNER join commande c where pc.produit_id = p.id and pc.commande_id= c.id and c.panier_id = pa.id and ? = pa.utilisateur_id ");
              pt.setInt(1,user_id);  
             ResultSet res= pt.executeQuery();
             while(res.next())
                
             {
               PanierDetail s = new PanierDetail(res.getString(1),res.getInt(2),res.getInt(3),res.getInt(4));
             
               tab.add(s);
                 System.out.println("");
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
         }
        tab.forEach(e->{System.out.println(e);});
       return tab;
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public int panierTotal (int prix , int quantite) {
         
        int s =prix*quantite;
         
         return s;
      }
      
      public Panier getPanierByUser (int id) {
        Panier panier = new Panier();
        try {
            String requete = "select * from panier where ? = utilisateur_id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                panier.setId(rs.getInt("id"));
                panier.setTotal(rs.getInt("total"));
                panier.setUser_id(id);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return panier;
    }
    
      
}
   
