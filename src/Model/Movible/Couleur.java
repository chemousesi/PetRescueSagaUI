package Model.Movible;

public enum Couleur {
    jaune("Jaune", 255, 255, 0), rouge("Rouge", 255, 0, 0), bleu("Bleu", 0, 0, 255), violet("Violet", 127, 0, 255),
    orange("Orange", 255, 165, 0), gris("Gris", 128, 128, 128);

    private String couleur;
    private int red;
    private int green;
    private int blue;

    private Couleur(String couleur, int red, int green, int blue) {
        this.couleur = couleur;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return String.valueOf(couleur.charAt(0));
    }

    public String getCouleur() {
        return couleur;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
