package Model.Environnement;

import java.io.Serializable;

public class Conditions implements Serializable, Cloneable {
    /**
     * désigne les conditions pour qu'un niveau soit gagné.
     */
    private static final long serialVersionUID = 1L;
    private int nbAnimauxASauver;
    private int nbPointsAGagner;

    public Conditions(int nbAnimauxASauver, int nbPointsAGagner) {
        this.nbAnimauxASauver = nbAnimauxASauver;
        this.nbPointsAGagner = nbPointsAGagner;
    }

    public int getNbAnimauxASauver() {
        return nbAnimauxASauver;
    }

    public int getNbPointsAGagner() {
        return nbPointsAGagner;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
