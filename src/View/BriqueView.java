package View;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Graphics.*;
import java.io.File;
import java.io.IOException;

import Model.Movible.Brique;
import Model.Movible.Couleur;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BriqueView extends JComponent {
    Brique brique;
    Color color;
    int c;
    int l;
    BufferedImage image;

    public BriqueView(int i, int j, Color c) {
        this.l = j;
        this.c = j;
        this.color = c;
        chargerImage("imgs./dog.png");
        setSize(50, 50);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(c, l, 50, 50);
        g.drawImage(image, 20, 20, null);
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
            // TODO Auto-generated method stub
            super.mouseClicked(e);
            System.out.println("ligne : " + l + " colonne : " + c);
        }

    }

}
