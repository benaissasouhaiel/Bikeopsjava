/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;
import javafx.beans.property.StringProperty;

/**
 *
 * @author laoui
 */
public class Commande {

    private String date;
    private int id;
    private String etat;
    private String typePaiment;
    private int panier_id; 
    private int livraison_id;
    

    public Commande(int id,String date,  String etat,String typePaiment, int panier_id,int livraison_id) {
           this.id = id;
        this.date = date;
    
        this.etat = etat;
        this.typePaiment = typePaiment;
        this.panier_id = panier_id;
        this.livraison_id = livraison_id;
    }

    public Commande(String date, String etat) {
        this.date = date;
        this.etat = etat;
    }

    public Commande(String date, String etat,String typePaiment,int panier_id,int livraison_id) {
        this.date = date;
        this.etat = etat;
        this.typePaiment = typePaiment;
        this.livraison_id = livraison_id;
        this.panier_id = panier_id;

    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public int getLivraison_id() {
        return livraison_id;
    }

    public void setLivraison_id(int livraison_id) {
        this.livraison_id = livraison_id;
    }
    

    public String getTypePaiment() {
        return typePaiment;
    }

    public void setTypePaiment(String typePaiment) {
        this.typePaiment = typePaiment;
    }
    
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "date=" + date + ", id=" + id + ", etat=" + etat + ", typePaiment=" + typePaiment + ", panier_id=" + panier_id + ", livraison_id=" + livraison_id + '}';
    }


    
    

}
