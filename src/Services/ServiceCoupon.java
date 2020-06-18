/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import utils.ConnexionBD;



public class ServiceCoupon {
    
  
    Connection conx;
    public ServiceCoupon()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Coupon c) {
        try {
            String requete = "insert into coupon (code,pourcentage) values(?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(1, c.getCode());
            pst.setFloat(2, c.getPourcentage());
            
            pst.executeUpdate();
            System.out.println("code generated !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
  
    public void DeleteClasse(Coupon c) {
        try {
            String requete = "delete from classes where ? = code";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(1, c.getCode());
            pst.executeUpdate();
            System.out.println("code Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
}
    
    public static char selectAChar(String s){
        Random random = new Random();
        int index = random.nextInt(s.length());
        return s.charAt(index);
}
    
    public void generateCoupons () {
        int[] pourcentages = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int j = 0; j < 20; j++) {
           String s = "";
           int randomPourcentage = getRandom(pourcentages);
           for (int i = 0; i < 15; i++) {
               s += selectAChar(characters);
           }
           Coupon coupon = new Coupon();
           coupon.setCode(s);
           coupon.setPourcentage(randomPourcentage);
           addClass(coupon);
       }        
    }
    
    
    public Coupon getCouponByCode(String code) {
      Coupon c = new Coupon();
            try {
        String requete = "select * from coupon where ? = code";
        PreparedStatement pst = conx.prepareStatement(requete);
        pst.setString(1, code);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c.setCode(rs.getString("code"));
            c.setPourcentage(rs.getFloat("pourcentage"));
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
  }
    
}
