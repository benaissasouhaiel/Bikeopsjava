/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Souhaiel
 */
public class Produit {

    private int id;
    private int qte;
    private int prix;
    private String name;
    private String desc;
    private Categorie cat;

    /**
     *
     */
    public Produit() {
    }

    public Produit(int id, int qte, int prix, String name, String desc) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.name = name;
        this.desc = desc;
    }

    public Produit(int id, int qte, int prix, String name, String desc, Categorie cat) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.name = name;
        this.desc = desc;
        this.cat = cat;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", qte=" + qte + ", prix=" + prix + ", name=" + name + ", desc=" + desc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.qte;
        hash = 97 * hash + this.prix;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.desc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        return true;
    }

}
