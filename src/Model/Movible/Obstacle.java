package Movible;

public class Obstacle extends Brique {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String icon;/// pour la partie graphique.

    public Obstacle(Couleur couleur) {
        super(couleur);
    }

    @Override
    public boolean estMobile() {
        return false;
    }

    @Override
    public String toString() {
        return "\u0800";
    }

}
