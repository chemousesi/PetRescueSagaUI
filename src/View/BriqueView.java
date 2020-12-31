package View;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.io.File;
import java.io.IOException;
import Model.Movible.Brique;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BriqueView extends ComponentView {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Brique brique;
    Color color;
    BufferedImage image;

    public BriqueView(int i, int j, Color c) {
        super(i, j);
        this.color = c;
        this.addMouseListener(new MyMouseAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        // g.fillRect(15, 10, getWidth() - 30, getHeight() - 20);
        g.fillRect(10, 10, getWidth() - 10, getHeight() - 10);

    }

    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("brique cliquée");
            System.out.println("colonne : " + BriqueView.this.c);
        }

    }

}
