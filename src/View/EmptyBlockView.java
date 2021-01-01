package View;

import java.awt.Color;
import java.awt.Graphics;

public class EmptyBlockView extends ComponentView {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    EmptyBlockView(int l, int c) {
        super(l, c);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
    }

}
