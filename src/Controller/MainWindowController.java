package Controller;

import Model.Environnement.Jeu;
import Model.Environnement.Joueur;
import Model.Environnement.Partie;

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
        partie = Jeu.lancerPartie(joueur);
        return partie;
    }

    public Partie getPartie() {
        return partie;
    }
}
