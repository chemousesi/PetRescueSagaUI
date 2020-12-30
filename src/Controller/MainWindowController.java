package Controller;

import Model.Environnement.Joueur;

public class MainWindowController {
    private Joueur joueur;

    public MainWindowController() {
        super();

    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
