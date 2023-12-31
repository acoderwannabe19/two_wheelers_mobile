/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjeemobile.entities;



/**
 *
 * @author dell
 */


public class Employe extends Client {

    private short actif;


    private Employe managerId;


    private Magasin magasinId;

    public Employe() {
    }

    public Employe(Integer id, String prenom, String nom, String telephone, String email, String adresse, String ville, String etat, String codeZip, short actif, Employe managerId, Magasin magasinId) {
        super(id, prenom, nom, telephone, email, adresse, ville, etat, codeZip);
        this.actif = actif;
        this.managerId = managerId;
        this.magasinId = magasinId;
    }

    public Employe(Integer id, String prenom, String nom, String telephone, String email, String adresse, String ville, String etat, String codeZip, short actif, Magasin magasinId) {
        super(id, prenom, nom, telephone, email, adresse, ville, etat, codeZip);
        this.actif = actif;
        this.magasinId = magasinId;
    }


    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }

    public short getActif() {
        return actif;
    }

    public void setActif(short actif) {
        this.actif = actif;
    }


    public Employe(short actif, Employe managerId, Magasin magasinId) {
        this.actif = actif;
        this.managerId = managerId;
        this.magasinId = magasinId;
    }

    public Employe getManagerId() {
        return managerId;
    }

    public void setManagerId(Employe managerId) {
        this.managerId = managerId;
    }

    public Magasin getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Magasin magasinId) {
        this.magasinId = magasinId;
    }



}
