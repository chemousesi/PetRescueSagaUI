package Model.Movible;

public class Obstacle extends Brique {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}
