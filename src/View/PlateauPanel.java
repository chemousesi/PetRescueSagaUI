package View;

import java.awt.Color;

import java.awt.*;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;

import Controller.MainWindowController;
import Model.Movible.Case;

public class PlateauPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int colonnes;
    private int lignes;
    private MainWindowController controller;
    private MainWindow mainWindow;
    private BufferedImage image;

    // audio part

    public PlateauPanel(MainWindow mainWindow, MainWindowController controller) {
        super();
        this.controller = controller;
        this.mainWindow = mainWindow;
        chargerImage("imgs/bg.jpg");
        // this.setBackground(new Color(0, 0, 0, 80));
        this.setOpaque(false);
        this.lignes = this.controller.getPartie().getNiveauAJouer().getPlateau().lignes - 2;
        this.colonnes = this.controller.getPartie().getNiveauAJouer().getPlateau().colonnes - 2;
        GridLayout gridLayout = new GridLayout(lignes, colonnes);
        this.remplireGridLayoutFromPlateau();
        this.repaint();
        gridLayout.setHgap(0);
        gridLayout.preferredLayoutSize(this);
        this.setLayout(gridLayout);

    }

    private void remplireGridLayoutFromPlateau() {
        for (int i = 1; i <= lignes; i++) {
            for (int j = 1; j <= colonnes; j++) {
                Case temp = this.controller.getPartie().getNiveauAJouer().getPlateau().getCase(i, j);
                if (temp.estVide()) {
                    this.add(new EmptyBlockView(i, j));
                } else {
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
            g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);

        }

        class MyMouseAdapter extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PlateauPanel.this.controller.getAudioGame().lanceDestructionSound();

                try {

                    if (!controller.getBombActive() && !controller.getMissileActive()) {
                        PlateauPanel.this.controller.getPartie().jouerUnTour(l, c);

                    }

                    if (controller.getMissileActive()) {
                        // voir si on veut utiliser un missile
                        controller.getPartie().utiliserMissile(c);
                        controller.setMissileActive(false);

                    }
                    if (controller.getBombActive()) {
                        controller.getPartie().utiliserBomb(l, c);
                        controller.setBombActive(false);
                    }

                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }

                PlateauPanel.this.removeAll();
                PlateauPanel.this.revalidate();
                PlateauPanel.this.remplireGridLayoutFromPlateau();
                PlateauPanel.this.repaint();
                if (PlateauPanel.this.controller.getPartie().estGagne()) {
                    PlateauPanel.this.controller.getAudioGame().lanceGangnerSound();
                    PlateauPanel.this.removeAll();
                    PlateauPanel.this.revalidate();
                    PlateauPanel.this.controller.getPartie().passerNiveauSuivant();
                    JOptionPane.showMessageDialog(PlateauPanel.this, "La partie est gagné !!");
                    PlateauPanel.this.mainWindow.getCardLayout().show(PlateauPanel.this.mainWindow.getJContentPane(),
                            "3");
                } else if (PlateauPanel.this.controller.getPartie().estPerdue() && !PlateauPanel.this.controller
                        .getPartie().getNiveauAJouer().getAides().pasDeBombesEtDeMissilesDisponibles()) {
                    PlateauPanel.this.controller.getAudioGame().lancePerdreSound();

                    int choix = JOptionPane.showConfirmDialog(PlateauPanel.this.mainWindow.getJContentPane(),
                            "Voulez-vous réessayer ?", "", JOptionPane.YES_NO_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        PlateauPanel.this.controller.jouerEnModeGraphique();
                        PlateauPanel.this.removeAll();
                        PlateauPanel.this.revalidate();
                        PlateauPanel.this.remplireGridLayoutFromPlateau();
                        PlateauPanel.this.repaint();
                        PlateauPanel.this.mainWindow.getGamePane().initialise(mainWindow, controller);
                        // PlateauPanel.this.mainWindow.getGamePane().repaint();
                    } else {
                        PlateauPanel.this.removeAll();
                        PlateauPanel.this.revalidate();
                        PlateauPanel.this.mainWindow.getCardLayout()
                                .show(PlateauPanel.this.mainWindow.getJContentPane(), "3");

                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                // rect.setSize((int) rect.getWidth() - 10, (int) rect.getHeight() - 10);
                // BriqueView.this.setBounds(15, 15, getWidth() - 30, getHeight() - 30);
                BriqueView.this.setSize(getWidth() - 5, getHeight() - 5);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                // rect.setSize((int) rect.getWidth() + 10, (int) rect.getHeight() + 10);
                BriqueView.this.setSize(getWidth() + 5, getHeight() + 5);
                repaint();
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
