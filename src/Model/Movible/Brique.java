package Model.Movible;

public class Brique extends Element {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Couleur couleur;

    public Brique(Couleur couleur) {
        this.couleur = couleur;
    }

    public boolean estMobile() {
        return true;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return couleur.toString();
    }
}
