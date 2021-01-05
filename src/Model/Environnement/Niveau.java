package Model.Environnement;

import java.io.Serializable;

public class Niveau implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int numero;
    private Plateau plateau;
    private Conditions conditionsDeGagner;
    private Aide aides;

    public Niveau(int numero, Plateau plateau, Conditions conditions, Aide aides) {
        this.numero = numero;
        this.plateau = plateau;
        this.conditionsDeGagner = conditions;
        this.aides = aides;
    }

    public void printConditionsGagner() {
        System.out.println("\t\t\t ########### Conditions Pour Gagner ###########");
        System.out.println("\t\t\t #                                            #");
        System.out.println("\t\t\t #   Nombre de points : " + this.conditionsDeGagner.getNbPointsAGagner()
                + "                   #");
        System.out.println("\t\t\t #   Nombre d'animaux : " + this.conditionsDeGagner.getNbAnimauxASauver()
                + "                     #");
        System.out.println("\t\t\t #                                            #");
        System.out.println("\t\t\t ##############################################");
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Conditions getConditionsDeGagner() {
        return conditionsDeGagner;
    }

    public Aide getAides() {
        return aides;
    }

    public int getNumero() {
        return numero;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    @Override
    public Object clone() throws CloneNotSupportedException { /// sert à avoir une nouvelle instance avec les mm valeurs
                                                              /// que this.
        return new Niveau(numero, (Plateau) plateau.clone(), (Conditions) conditionsDeGagner.clone(),
                (Aide) aides.clone());
    }
}
