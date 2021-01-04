package Model.Environnement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Model.Movible.Case;

public class Partie {
    private Niveau niveauAJouer;
    private Joueur joueur;
    private int nbPointsGangerParLeJoueur;
    private int nbAnimauxSauves;
    private boolean abondonner;

    public Partie(Niveau niveau, Joueur joueur) {
        this.niveauAJouer = niveau;
        this.joueur = joueur;
        this.nbPointsGangerParLeJoueur = 0;
        this.nbAnimauxSauves = 0;
        this.abondonner = false;
    }

    public boolean estGagne() {
        return niveauAJouer.getConditionsDeGagner().getNbAnimauxASauver() == this.nbAnimauxSauves
                && niveauAJouer.getConditionsDeGagner().getNbPointsAGagner() <= this.nbPointsGangerParLeJoueur;
    }

    public boolean estPerdue() {
        /// faire le traitement pour savoir si la partie est perdue.
        int nbPointsGagnes = 0;
        Plateau clone = null;
        try {
            clone = (Plateau) this.niveauAJouer.getPlateau().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < clone.colonnes - 1; i++) {
            for (int j = 1; j < clone.lignes - 1; j++) {
                nbPointsGagnes += clone.detruire(j, i, false);
                if (nbPointsGagnes != 0)
                    return false;
            }
        }
        if (this.nbPointsGangerParLeJoueur >= this.niveauAJouer.getConditionsDeGagner().getNbPointsAGagner()
                && this.nbAnimauxSauves == this.niveauAJouer.getConditionsDeGagner().getNbAnimauxASauver())
            return false;
        return true;
    }

    private boolean abondonner() {
        return this.abondonner;
    }

    public void setAbondonner(boolean abondonner) {
        this.abondonner = abondonner;
    }

    public void jouerUnePartieModeTexte() throws CloneNotSupportedException {
        int coordX, coordY;
        Scanner scanner = new Scanner(System.in);

        /// ajouter des retouches ici.
        while (!estGagne() && !abondonner() && !estPerdue()) { /// tant que la parite n'es pas gagné
            this.niveauAJouer.printConditionsGagner();
            this.niveauAJouer.getPlateau().afficher();

            System.out.println("                     Nombre de points gagnés : " + this.nbPointsGangerParLeJoueur
                    + "  Nombre d'animaux sauvés : " + this.nbAnimauxSauves + "\n");

            System.out.println("d : Detruire , m : Missile , i : Indice, b : Bombe");
            String reponse = scanner.next();
            // traiter le cas de m
            if (reponse.equalsIgnoreCase("m")) {

                if (this.niveauAJouer.getAides().missileDisponible()) {
                    System.out.println("Indiquer la colonne à détruire ");
                    try {
                        int colADetruire = scanner.nextInt();
                        this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau()
                                .detruireColonneParMissile(colADetruire);
                        this.niveauAJouer.getAides().enleverMissile();

                    } catch (InputMismatchException e) {
                        scanner.next();
                        System.out.println("Entreée non valide");
                    }
                } else {
                    System.out.println("plus de missile disponible !");
                }

            } else if (reponse.equalsIgnoreCase("d")) {
                System.out.println("--> Indiquer la case à detruire: ");
                // il faut rajouter l'utilisation de missile ici
                try {

                    System.out.print("Tapez le n° de la colonne : ");
                    coordX = scanner.nextInt();
                    System.out.print("Tapez le n° de la ligne   : ");
                    coordY = scanner.nextInt();
                    this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau().detruire(coordY, coordX, true);

                } catch (InputMismatchException e) {
                    scanner.next();// on pourra traiter le problème du missile ici
                    System.out.println("Entrée non valide");

                }
            } else if (reponse.equalsIgnoreCase("i")) {
                if (this.niveauAJouer.getAides().indiceDisponible()) {
                    ArrayList<Integer> arraylist = this.niveauAJouer.getPlateau().avoirBonCase();
                    System.out.println("*** la meilleure case à detruire est [ " + arraylist.get(0) + " : "
                            + arraylist.get(1) + " ] ***");
                    System.out.println("*** le score à gagner est : " + arraylist.get(2) + " ***");
                    this.niveauAJouer.getAides().enleverIndice();

                } else {
                    System.out.println("plus d'indice disponible !");
                }
            } else if (reponse.equalsIgnoreCase("b")) {
                if (this.niveauAJouer.getAides().bombesDisponible()) {
                    int l, c;
                    System.out.println("--> Indiquer la case à detruire: ");
                    try {

                        System.out.print("Tapez le n° de la colonne : ");
                        c = scanner.nextInt();
                        System.out.print("Tapez le n° de la ligne   : ");
                        l = scanner.nextInt();
                        Case caseLC = this.niveauAJouer.getPlateau().getCase(l, c);
                        if (caseLC.isActive() && !caseLC.estVide() && caseLC.estUneBrique()
                                && caseLC.getElement().estMobile()) {
                            this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau().detruireCasesParBombe(l,
                                    c);
                            this.niveauAJouer.getAides().enleverBombe();
                        } else {
                            System.out.println("*** La case choisie ne se détruit pas ***");
                        }
                    } catch (InputMismatchException e) {
                        scanner.next();// on pourra traiter le problème du missile ici
                        System.out.println("Entrée non valide");

                    }
                }
            }
            this.niveauAJouer.getPlateau().reorganiserPlateau();
            this.nbAnimauxSauves += this.niveauAJouer.getPlateau().animalSauve();

        }
        if (estGagne()) {
            passerNiveauSuivant();
            System.out.println("Le partie est gagné !!!");
        } else if (abondonner) {
            /// traitement ici
        } else {
            System.out.println("La partie est perdue !!");
        }
    }

    public void passerNiveauSuivant() {
        this.joueur.incrementerNivActuel();
        this.joueur.incrementeScore(nbPointsGangerParLeJoueur + 10 * nbAnimauxSauves);
        this.joueur.addElemToHistorique(this.niveauAJouer.getNumero(), nbPointsGangerParLeJoueur);
    }

    public void jouerUnTour(int l, int c) throws CloneNotSupportedException {
        this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau().detruire(l, c, false);
        this.niveauAJouer.getPlateau().reorganiserPlateau();
        this.nbAnimauxSauves += this.niveauAJouer.getPlateau().animalSauve();
        // System.out.println(this.nbPointsGangerParLeJoueur + " " +
        // this.nbAnimauxSauves);
    }

    public boolean utiliserMissile(int c) throws CloneNotSupportedException {
        // retourne true si il y'a des missile et leur utilisation se fait avec succès,
        // false s'il en reste plus

        if (this.niveauAJouer.getAides().missileDisponible()) {
            this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau().detruireColonneParMissile(c);
            this.niveauAJouer.getAides().enleverMissile();
            this.niveauAJouer.getPlateau().reorganiserPlateau();
            this.nbAnimauxSauves += this.niveauAJouer.getPlateau().animalSauve();
            return true;
        } else {
            return false;
        }

    }

    public boolean utiliserBomb(int l, int c) throws CloneNotSupportedException {
        // retourne true si il y'a des bombes et leur utilisation se fait avec succès,
        // false s'il en reste plus

        if (this.niveauAJouer.getAides().bombesDisponible()) {
            this.nbPointsGangerParLeJoueur += this.niveauAJouer.getPlateau().detruireCasesParBombe(l, c);
            this.niveauAJouer.getAides().enleverBombe();
            this.niveauAJouer.getPlateau().reorganiserPlateau();
            this.nbAnimauxSauves += this.niveauAJouer.getPlateau().animalSauve();
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Integer> utiliserIndice() throws CloneNotSupportedException {
        // retourne true si il y'a des bombes et leur utilisation se fait avec succès,
        // false s'il en reste plus

        ArrayList<Integer> arraylist = this.niveauAJouer.getPlateau().avoirBonCase();

        this.niveauAJouer.getAides().enleverIndice();

        return arraylist;

    }

    public Niveau getNiveauAJouer() {
        return niveauAJouer;
    }

    public int getNbPointsGangerParLeJoueur() {
        return nbPointsGangerParLeJoueur;
    }

    public int getNbAnimauxSauves() {
        return nbAnimauxSauves;
    }

    public void setNiveauAJouer(Niveau niveauAJouer) {
        this.niveauAJouer = niveauAJouer;
    }

}
