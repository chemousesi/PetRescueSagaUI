package Model.Movible;

import java.io.Serializable;

public class Case implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Element element;
    private boolean active;

    public Case() {

    }

    public Case(Element element, boolean active) {
        this.element = element;
        this.active = active;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public boolean estVide() {
        return (element == null);
    }

    public void vider() {
        element = null;
    }

    @Override
    public String toString() {
        if (this.active) {
            if (this.estVide()) {
                return ".";// quand elle est vide (peut être se vide dans le jeu)
            } else {
                return element.toString();
            }
        } else {
            return " ";// quand c'est pas active on on affiche espace
        }

    }

    public boolean isActive() {
        return active;
    }

    public boolean estUneBrique() {
        return this.getElement() instanceof Brique;
    }

    public boolean estUnAnimal() {
        return this.getElement() instanceof Animal;
    }

    public Brique getBrique() {
        if (this.estUneBrique())
            return (Brique) this.getElement();
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return new Case(this.element == null ? null : (Element) this.element.clone(), active);
    }
}
