package Model.Environnement;

import java.io.Serializable;

public class ElementHistorique implements Serializable {

    /**
     * pour stocker et enregistrer le nombre de points que l'user à gagner dans
     * chaque niveau.
     */
    private static final long serialVersionUID = 1L;
    private int niveau;
    private int scoreGagneDansLeNiveau;

    ElementHistorique(int niveau, int scoreGagneDansLeNiveau) {
        this.setNiveau(niveau);
        this.setScoreGagneDansLeNiveau(scoreGagneDansLeNiveau);
    }

    public int getScoreGagneDansLeNiveau() {
        return scoreGagneDansLeNiveau;
    }

    public void setScoreGagneDansLeNiveau(int scoreGagneDansLeNiveau) {
        this.scoreGagneDansLeNiveau = scoreGagneDansLeNiveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "** Le niveau [ " + this.niveau + " ] a été gagné avec un score de : " + this.scoreGagneDansLeNiveau;
    }
}
