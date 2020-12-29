package Model.Movible;

public class Animal extends Element {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nom;
    private String icon;

    public Animal(String nom, String icon) {
        this.nom = nom;
        this.icon = icon;
    }

    public String getNom() {
        return nom;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        // return Character.toString(nom.charAt(0)).toUpperCase();
        return "$";
    }

}
// ajouter des animaux comme sous classes