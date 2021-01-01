package Controller;

import Model.Environnement.Jeu;
import Model.Environnement.Joueur;
import Model.Environnement.Niveau;
import Model.Environnement.Partie;
import Model.Environnement.Plateau;

public class MainWindowController {
    private Joueur joueur;
    private Partie partie;

    public MainWindowController() {
        super();

    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Partie jouerEnModeGraphique() {
        try {
            partie = Jeu.lancerPartie(joueur);
            partie.setNiveauAJouer((Niveau) Jeu.getNiveau(joueur.getniveauActuel() - 1).clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return partie;
    }

    public Partie getPartie() {
        return partie;
    }
}
