package Model.Environnement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Joueur implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nom;
    private String nomUtilisateur;
    private int score;
    private int niveauActuel;
    private LinkedList<ElementHistorique> historique = new LinkedList<>();

    public Joueur(String nom, String nomUser) {
        this.nom = nom;
        this.score = 0;
        this.niveauActuel = 2;
        this.nomUtilisateur = nomUser;
    }

    public void incrementeScore(int score) {
        this.score += score;
    }

    public void setniveauActuel(int niveauActuel) {
        this.niveauActuel = niveauActuel;
    }

    public void incrementerNivActuel() {
        this.niveauActuel++;
    }

    public String getnom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public int getniveauActuel() {
        return niveauActuel;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void afficherHistoriqueConsole() {
        if (this.historique.isEmpty()) {
            System.out.println("*** Historique est vide ***");
        } else
            for (ElementHistorique elementHistorique : historique) {
                System.out.println(elementHistorique);
            }
    }

    public void addElemToHistorique(int niveau, int nbPointsGagnees) {
        this.historique.addFirst(new ElementHistorique(niveau, nbPointsGagnees));
        if (this.historique.size() > 5)
            this.historique.removeLast();
    }

    @Override
    public boolean equals(Object obj) {
        return this.nomUtilisateur.equals(((Joueur) obj).nomUtilisateur);
    }

    @Override
    public String toString() {
        return "Nom : " + this.nom + "\nNiveau Actuel : " + this.niveauActuel + "\nScore : " + this.score;
    }

    public LinkedList<ElementHistorique> getHistorique() {
        return historique;
    }

}
