/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjeemobile.entities;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author dell
 */


public class Stock implements Serializable {

    private int quantite;

    private Produit produit;

    private Magasin magasin;

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    public Stock() {
    }

    public Stock(int quantite, Produit produit, Magasin magasin) {

        this.quantite = quantite;
        this.produit = produit;
        this.magasin = magasin;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return quantite == stock.quantite && Objects.equals(produit, stock.produit) && Objects.equals(magasin, stock.magasin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantite, produit, magasin);
    }

    public String toString() {
        return produit + " : " + Integer.toString(quantite);
    }
    
}
