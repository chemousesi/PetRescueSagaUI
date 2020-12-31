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

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int colonnes;
    private int lignes;
    int currentX, currentY;
    int recWidth, recHeight;
    MainWindowController controller;
    Color color = Color.red;

    BufferedImage image;

    public PlateauPanel(MainWindowController controller) {
        super();
        this.controller = controller;
        chargerImage("imgs/bg.jpg");
        this.lignes = this.controller.getPartie().getNiveauAJouer().getPlateau().lignes - 2;
        this.colonnes = this.controller.getPartie().getNiveauAJouer().getPlateau().colonnes - 2;
        GridLayout gridLayout = new GridLayout(lignes, colonnes);
        this.remplireGridLayoutFromPlateau();
        gridLayout.setHgap(0);
        gridLayout.preferredLayoutSize(this);
        this.setLayout(gridLayout);
    }

    private void remplireGridLayoutFromPlateau() {
        for (int i = 1; i <= lignes; i++) {
            for (int j = 1; j <= colonnes; j++) {
                Case temp = this.controller.getPartie().getNiveauAJouer().getPlateau().getCase(i, j);
                if (!temp.estUnAnimal()) {
                    if (temp.getElement().estMobile())
                        // this.add(new BriqueView(i, j, new
                        // Color(temp.getBrique().getCouleur().getRed(),
                        this.add(new BriqueView(i, j, color));
                    // temp.getBrique().getCouleur().getGreen(),
                    // temp.getBrique().getCouleur().getBlue())));
                    else
                        this.add(new ObstacleView(i, j));
                } else {
                    this.add(new AnimalView(i, j, "imgs/dog.png"));
                }
            }

        }
        revalidate();

    }

    public int getColonnes() {
        return colonnes;
    }

    public int getLignes() {
        return lignes;
    }

    // @Override
    // protected void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // remplireGridLayoutFromPlateau();
    // }

    public class BriqueView extends ComponentView {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private Color color;

        public BriqueView(int i, int j, Color c) {
            super(i, j);
            this.color = c;
            this.addMouseListener(new MyMouseAdapter());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(20, 10, getWidth() - 40, getHeight() - 20);
        }

        class MyMouseAdapter extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PlateauPanel.this.color = Color.black;
                PlateauPanel.this.removeAll();
                remplireGridLayoutFromPlateau();
            }

        }

    }

    public void chargerImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
