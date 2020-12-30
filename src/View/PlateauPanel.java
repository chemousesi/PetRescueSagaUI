package View;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

import Model.Environnement.Plateau;

public class PlateauPanel extends JPanel {

    private int colonnes;
    private int lignes;
    private int longBrique;
    private Plateau plateau;
    private BriqueView[][] tabBriques;

    public PlateauPanel(int col, int lig) {
        super();
        this.colonnes = col;
        this.lignes = lig;

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        for (int i = 30; i <= 300; i += 30) {
            for (int j = 30; j <= 300; j += 30) {
                g.drawRect(i, j, 30, 30);
            }
        }

    }

    public int getColonnes() {
        return colonnes;
    }

    public int getLignes() {
        return lignes;
    }

}
