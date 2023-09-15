/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjeemobile.entities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author dell
 */


public class ArticleCommande implements Serializable {


    private Commande numeroCommande;


    private int ligne;


    private Produit produitId;

    private int quantite;

    private BigDecimal prixDepart;
    private BigDecimal remise;

    public ArticleCommande(Commande numeroCommande, int ligne, Produit produitId, int quantite, BigDecimal prixDepart, BigDecimal remise) {
        this.numeroCommande = numeroCommande;
        this.ligne = ligne;
        this.produitId = produitId;
        this.quantite = quantite;
        this.prixDepart = prixDepart;
        this.remise = remise;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public Produit getProduitId() {
        return produitId;
    }

    public void setProduitId(Produit produitId) {
        this.produitId = produitId;
    }

    public Commande getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(Commande numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public ArticleCommande() {
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(BigDecimal prixDepart) {
        this.prixDepart = prixDepart;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }




    
}
