/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
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
    PreparedStatement pt = conx.prepareStatement(" insert into Panier (id, lineItems, total)"
        + " values (?, ?, ?, ?)");
            //pt.setInt(1,t.getId_Tache());
            pt.setInt(1,sc.getId());
            pt.setInt(2,sc.getLineitems());
            pt.setInt(3,sc.getTotal());
                       
            
            pt.execute();
        }catch (SQLException ex)
            {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
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
    
       public void modifierPanier (int Id, int LineItems,int total)
    {
        try {
            PreparedStatement pt= conx.prepareStatement("update Pnaier set  total=?, Id=?, LineItems=?  where Id_Panier=?");
            pt.setInt(1,Id);
            pt.setInt(2,LineItems);
            pt.setInt(3,total);
            
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
    
    
    
    
      public List<Panier> displayAll() {
      // L'id du freelancer dans la requette est statique il faut le changer (session) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList <Panier> tab = new ArrayList ();     
        
    
         try {
             PreparedStatement pt =conx.prepareStatement("select id, LineItems from Panier");
               ResultSet res= pt.executeQuery();
             while(res.next())
                 
             {
               Panier s = new Panier(res.getInt(1),res.getInt(2));
                 tab.add(s);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       return tab;
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
