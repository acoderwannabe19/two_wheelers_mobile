/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjeemobile.entities;

import java.io.Serializable;
import java.util.Date;



/**
 *
 * @author dell
 */


public class Commande implements Serializable {


    private Integer numero;


    private Client clientId;

    private short statut;


    private Date dateCommande;

    private Date dateLivraisonVoulue;

    private Date dateLivraison;


    private Magasin magasinId;



    private Employe vendeurId;

    public Commande() {
        // Initialize date fields with default values
//        this.dateCommande = new Date(2020, 4, 23);
//        this.dateLivraisonVoulue = new Date(2020, 4, 23);
//        this.dateLivraison = new Date(2020, 4, 23);
    }

    public Commande(Integer numero, Client clientId, short statut, Date dateCommande, Date dateLivraisonVoulue, Date dateLivraison, Magasin magasinId, Employe vendeurId) {
        this.numero = numero;
        this.clientId = clientId;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateLivraisonVoulue = dateLivraisonVoulue;
        this.dateLivraison = dateLivraison;
        this.magasinId = magasinId;
        this.vendeurId = vendeurId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public short getStatut() {
        return statut;
    }

    public void setStatut(short statut) {
        this.statut = statut;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraisonVoulue() {
        return dateLivraisonVoulue;
    }

    public void setDateLivraisonVoulue(Date dateLivraisonVoulue) {
        this.dateLivraisonVoulue = dateLivraisonVoulue;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }


    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Employe getVendeurId() {
        return vendeurId;
    }

    public void setVendeurId(Employe vendeurId) {
        this.vendeurId = vendeurId;
    }

    public Magasin getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Magasin magasinId) {
        this.magasinId = magasinId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero.toString() ;
    }

}
