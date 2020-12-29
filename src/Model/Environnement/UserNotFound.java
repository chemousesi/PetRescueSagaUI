package Model.Environnement;

public class UserNotFound extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final String joueurNonTrouvé = "Joueur non trouvé !!!";

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return joueurNonTrouvé;
    }
}
