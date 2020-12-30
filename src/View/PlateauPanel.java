package View;

import Controller.MainWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import Model.Environnement.Jeu;
import Model.Environnement.Plateau;

public class PlateauPanel extends JPanel {

    private int colonnes;
    private int lignes;
    private int longBrique;
    private Plateau plateau;
    private BriqueView[] tabBriques;
    int currentX, currentY;
    int recWidth, recHeight;
    MainWindowController controller;

    BufferedImage image;

    public PlateauPanel(int col, int lig, MainWindowController controller) {
        super();
        this.colonnes = col;
        this.lignes = lig;
        this.controller = controller;
        // this.setSize(100, 100);

        chargerImage("imgs/bg.jpg");
        this.addMouseListener(new MyMouseAdapter());

        GridLayout gridLayout = new GridLayout(4, 4);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        gridLayout.preferredLayoutSize(this);

        this.setLayout(gridLayout);

        initialiserNiveau1();

    }

    public int getColonnes() {
        return colonnes;
    }

    public int getLignes() {
        return lignes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //
        // g.drawImage(image, 0, 0, null);
        //
        // for (int i = 50; i <= 500; i += 50) {
        // for (int j = 50; j <= 400; j += 50) {
        // g.drawRect(i, j, 50, 50);
        // }
        // }
        // g.fillRect(currentX, currentY, recWidth, recHeight);
        //
    }

    class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            super.mouseClicked(e);

        }
    }

    public void chargerImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void initialiserNiveau1() {

        BriqueView b1 = new BriqueView(0, 5, Color.RED);
        BriqueView b2 = new BriqueView(0, 5, Color.GREEN);
        BriqueView b3 = new BriqueView(0, 5, Color.PINK);
        BriqueView b4 = new BriqueView(0, 5, Color.BLUE);
        BriqueView b5 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b6 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b7 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b8 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b9 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b10 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b11 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b12 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b13 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b14 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b15 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b16 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b17 = new BriqueView(0, 5, Color.CYAN);
        BriqueView b18 = new BriqueView(0, 5, Color.CYAN);

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(b10);
        this.add(b11);
        this.add(b12);
        this.add(b13);
        this.add(b14);
        this.add(b15);
        this.add(b16);
        this.add(b17);
        this.add(b18);

        // this.add(b5);

        // this.add(new JButton("A"));
        // this.add(new JButton("B"));

    }

}
