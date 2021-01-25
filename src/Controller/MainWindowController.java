package Controller;

import Model.Environnement.Jeu;
import Model.Environnement.Joueur;
import Model.Environnement.Niveau;
import Model.Environnement.Partie;
import View.AudioGame;

public class MainWindowController {
    private Joueur joueur;
    private Partie partie;
    private boolean missileActive = false;
    private boolean bombActive = false;
    private AudioGame audioGame = new AudioGame();

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
            if (partie != null)
                partie.setNiveauAJouer((Niveau) Jeu.getNiveau(joueur.getniveauActuel() - 1).clone());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return partie;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setMissileActive(boolean missileActive) {
        this.missileActive = missileActive;
    }

    public boolean getMissileActive() {
        return missileActive;
    }

    public void setBombActive(boolean bombActive) {
        this.bombActive = bombActive;
    }

    public boolean getBombActive() {
        return bombActive;
    }

    public AudioGame getAudioGame() {
        return audioGame;
    }

}
