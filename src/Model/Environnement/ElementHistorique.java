package Model.Environnement;

public class ElementHistorique {
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
