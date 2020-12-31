package View;

import java.awt.*;

public class ObstacleView extends ComponentView {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ObstacleView(int l, int c) {
        super(l, c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(10, 10, getWidth() - 10, getHeight() - 10);
    }

}
