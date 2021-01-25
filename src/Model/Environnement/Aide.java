package Model.Environnement;

import java.io.Serializable;

public class Aide implements Serializable, Cloneable { /// pour identifier les aides qu'un joueur peut avoir(missile,
    /// bombes et indices.
    private static final long serialVersionUID = 1L;

    private int nbMissiles;
    private int nbIndices;
    private int nbBombes;

    public Aide(int missiles, int indices, int bombes) {
        this.nbMissiles = missiles;
        this.nbIndices = indices;
        this.nbBombes = bombes;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public int getNbMissiles() {
        return nbMissiles;
    }

    public int getNbIndices() {
        return nbIndices;
    }

    public boolean missileDisponible() {
        return nbMissiles > 0;
    }

    public boolean indiceDisponible() {
        return nbIndices > 0;
    }

    public boolean bombesDisponible() {
        return nbBombes > 0;
    }

    public boolean enleverMissile() {
        if (missileDisponible()) {
            nbMissiles--;
            return true;
        }
        return false;
    }

    public boolean enleverIndice() {
        if (indiceDisponible()) {
            nbIndices--;
            return true;
        }
        return false;
    }

    public boolean enleverBombe() {
        if (bombesDisponible()) {
            nbBombes--;
            return true;
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean pasDeBombesEtDeMissilesDisponibles() {
        return missileDisponible() || bombesDisponible();
    }
}
