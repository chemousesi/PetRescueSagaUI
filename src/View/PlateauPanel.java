package View;

import Controller.MainWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import Model.Environnement.Plateau;
import Model.Movible.Case;

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

    public PlateauPanel(MainWindowController controller) {
        super();
        this.controller = controller;
        chargerImage("imgs/bg.jpg");
        this.addMouseListener(new MyMouseAdapter());
        this.lignes = this.controller.getPartie().getNiveauAJouer().getPlateau().lignes - 2;
        this.colonnes = this.controller.getPartie().getNiveauAJouer().getPlateau().colonnes - 2;
        GridLayout gridLayout = new GridLayout(lignes, colonnes);
        gridLayout.setHgap(0);
        gridLayout.preferredLayoutSize(this);
        remplireGridLayoutFromPlateau();
        this.setLayout(gridLayout);
    }

    private void remplireGridLayoutFromPlateau() {
        for (int i = 1; i <= colonnes; i++) {
            for (int j = 1; j <= lignes; j++) {
                Case temp = this.controller.getPartie().getNiveauAJouer().getPlateau().getCase(j, i);
                // this.add(temp.estUnAnimal() ? new AnimalView()
                // : temp.getElement().estMobile()
                // ? new BriqueView(i, j,
                // new Color(temp.getBrique().getCouleur().getRed(),
                // temp.getBrique().getCouleur().getGreen(),
                // temp.getBrique().getCouleur().getBlue()))
                // : new ObstacleView());
                if (!temp.estUnAnimal()) {
                    this.add(new BriqueView(i, j, new Color(255, 200, 100)));
                } else {
                    this.add(new AnimalView());
                }
            }

        }
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
    }

}
