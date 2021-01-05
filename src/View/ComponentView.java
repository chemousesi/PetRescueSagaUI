package View;

import javax.swing.JComponent;

public abstract class ComponentView extends JComponent {

    /**
     * vue d'un component sur le plateau une brique ou un animal
     */
    private static final long serialVersionUID = 1L;
    protected int l, c;

    ComponentView(int l, int c) {
        this.l = l;
        this.c = c;
    }

}
