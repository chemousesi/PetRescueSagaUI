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
     * le plateau sur le quel les niveaux sont déssinés et tout le jeu se déroule
     */
    private static final long serialVersionUID = 1L;
    private int colonnes;
    private int lignes;
    private MainWindowController controller;
    private MainWindow mainWindow;
    BufferedImage image;

    public PlateauPanel(MainWindow mainWindow, MainWindowController controller) {
        super();
        this.controller = controller;
        this.mainWindow = mainWindow;
        chargerImage("imgs/bg.jpg");
        // this.setBackground(new Color(0, 0, 0, 80)); // ici si on souhaite garder une
        // certaine ombre derrière
        this.setOpaque(false); // fait pour l'affichage complètement transparent
        // affecter le nombre de lignes et de colonnes le même que celui dans le model
        this.lignes = this.controller.getPartie().getNiveauAJouer().getPlateau().lignes - 2;
        this.colonnes = this.controller.getPartie().getNiveauAJouer().getPlateau().colonnes - 2;

        GridLayout gridLayout = new GridLayout(lignes, colonnes);

        this.remplireGridLayoutFromPlateau();
        this.repaint();

        gridLayout.setHgap(0);
        gridLayout.preferredLayoutSize(this);
        // utilisation d'une grid layout dans notre modelisation
        this.setLayout(gridLayout);

    }

    private void remplireGridLayoutFromPlateau() {
        /**
         * cette méthode fait le remplissage de la grille à partir du plateau du model
         * du niveau en question
         */
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
         * classe interne Brique view dans PlateauPanel, la brique est lié au plateau
         * sur lequel elle est déssinée la brique représente le carré coloré qu'on
         * détruit en jouant pour sauver les animaux
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
            /**
             * nous avons implémenter à l'interieur de la brique u Mouse Adapter pour
             * pouvoir récupérer les événement de la souris
             * 
             */

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // son de destruction quand on clique sur brique
                PlateauPanel.this.controller.getAudioGame().lanceDestructionSound();

                try {
                    // voir si on est entrain d'utliser un missile ou une bombe
                    if (!controller.getBombActive() && !controller.getMissileActive()) {
                        PlateauPanel.this.controller.getPartie().jouerUnTour(l, c);

                    }

                    // traitement du cas missile
                    if (controller.getMissileActive()) {

                        controller.getPartie().utiliserMissile(c);
                        controller.setMissileActive(false);

                    }

                    // traitement du cas bombe

                    if (controller.getBombActive()) {
                        controller.getPartie().utiliserBomb(l, c);
                        controller.setBombActive(false);
                    }

                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }

                // suppression de tout les éléments
                PlateauPanel.this.removeAll();
                PlateauPanel.this.revalidate();
                // remplissage nouveau
                PlateauPanel.this.remplireGridLayoutFromPlateau();
                PlateauPanel.this.repaint();

                if (PlateauPanel.this.controller.getPartie().estGagne()) {
                    // si on gagne à ce clique
                    PlateauPanel.this.controller.getAudioGame().lanceGangnerSound();
                    PlateauPanel.this.removeAll();
                    PlateauPanel.this.revalidate();
                    PlateauPanel.this.controller.getPartie().passerNiveauSuivant();
                    JOptionPane.showMessageDialog(PlateauPanel.this, "La partie est gagné !!");
                    PlateauPanel.this.mainWindow.getCardLayout().show(PlateauPanel.this.mainWindow.getJContentPane(),
                            "3");
                } else if (PlateauPanel.this.controller.getPartie().estPerdue() && !PlateauPanel.this.controller
                        .getPartie().getNiveauAJouer().getAides().pasDeBombesEtDeMissilesDisponibles()) {
                    // si on perd :

                    PlateauPanel.this.controller.getAudioGame().lancePerdreSound();

                    // on propose le choix si on souhaite rejouer ou pas
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

            // faire l'animation quand la souris passe au dessus d'une brique
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                BriqueView.this.setSize(getWidth() - 5, getHeight() - 5);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
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
