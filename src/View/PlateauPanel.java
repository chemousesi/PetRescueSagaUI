package View;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;

import Model.Environnement.Plateau;

public class PlateauPanel extends JPanel {

    private int colonnes;
    private int lignes;
    private int longBrique;
    private Plateau plateau;
    private BriqueView[][] tabBriques;
    int currentX, currentY;
    int recWidth, recHeight;

    public PlateauPanel(int col, int lig) {
        super();
        this.colonnes = col;
        this.lignes = lig;

        this.addMouseListener(new MyMouseAdapter());
    }

    public int getColonnes() {
        return colonnes;
    }

    public int getLignes() {
        return lignes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 50; i <= 500; i += 50) {
            for (int j = 50; j <= 500; j += 50) {
                g.drawRect(i, j, 50, 50);
            }
        }
        g.fillRect(currentX, currentY, recWidth, recHeight);

    }

    class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            super.mouseClicked(e);
            currentX = (int) e.getX();
            currentY = (int) e.getY();
            recHeight = 50;
            recWidth = 50;
            repaint();

        }
    }

}
