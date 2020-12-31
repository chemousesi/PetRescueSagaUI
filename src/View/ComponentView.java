package View;

import javax.swing.JComponent;
import java.awt.*;

public abstract class ComponentView extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected int l, c;

    ComponentView(int l, int c) {
        this.l = l;
        this.c = c;
    }

}
