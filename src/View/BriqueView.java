package View;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.io.File;
import java.io.IOException;
import Model.Movible.Brique;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BriqueView extends JComponent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Brique brique;
    Color color;
    int c;
    int l;
    BufferedImage image;

    public BriqueView(int i, int j, Color c) {
        this.l = j;
        this.c = j;
        this.color = c;
        // chargerImage("imgs./dog.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(0, 0, 50, 50);
        // g.drawImage(image, 20, 20, null);
    }

    public void chargerImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

    }

}
