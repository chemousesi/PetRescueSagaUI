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
import Model.Movible.Animal;
import Model.Movible.Brique;
import Model.Movible.Case;

public class PlateauPanel extends JPanel {

    private int colonnes;
    private int lignes;

    MainWindowController controller;

    BufferedImage image;

    public PlateauPanel(MainWindowController controller) {
        super();
        this.controller = controller;

        this.setOpaque(false);

        this.addMouseListener(new MyMouseAdapter());
        this.lignes = this.controller.getPartie().getNiveauAJouer().getPlateau().lignes - 2;
        this.colonnes = this.controller.getPartie().getNiveauAJouer().getPlateau().colonnes - 2;
        GridLayout gridLayout = new GridLayout(lignes, colonnes);
        gridLayout.setHgap(0);
        gridLayout.preferredLayoutSize(this);
        this.setLayout(gridLayout);

        remplireGridLayoutFromPlateau();
    }

    private void remplireGridLayoutFromPlateau() {
        for (int i = 1; i <= lignes; i++) {
            for (int j = 1; j <= colonnes; j++) {
                Case temp = this.controller.getPartie().getNiveauAJouer().getPlateau().getCase(i, j);

                if (temp.estVide()) {
                    this.add(new ObstacleView(i, j));

                }

                else {

                    if (!temp.estUnAnimal()) {
                        if (temp.getElement().estMobile())
                            this.add(new BriqueView(i, j,
                                    new Color(temp.getBrique().getCouleur().getRed(),
                                            temp.getBrique().getCouleur().getGreen(),
                                            temp.getBrique().getCouleur().getBlue())));
                        else
                            this.add(new ObstacleView(i, j));
                    } else {
                        this.add(new AnimalView(i, j, "imgs/cat.png"));
                    }
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

    public class BriqueView extends ComponentView {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

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
                PlateauPanel.this.controller.getPartie().getNiveauAJouer().getPlateau().detruire(BriqueView.this.l,
                        BriqueView.this.c, false);

                try {
                    PlateauPanel.this.controller.getPartie().getNiveauAJouer().getPlateau().reorganiserPlateau();
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                PlateauPanel.this.removeAll();

                PlateauPanel.this.remplireGridLayoutFromPlateau();
                PlateauPanel.this.revalidate();
            }

        }

    }
}
