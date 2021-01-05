package View;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Controller.MainWindowController;

public class GamePane extends JPanel {

    /**
     * cette vue designe le panneau qui englobe le plateau pannel
     */

    private static final long serialVersionUID = 1L;

    private MainWindowController controller;
    private MainWindow mainWindow;
    private PlateauPanel plateauPanel;

    private JLabel nomJoueur;
    private JLabel niveauActuel;
    private JLabel score;
    private JLabel conditionsPoints;
    private JLabel conditionsAnimal;

    private JPanel header = new JPanel();
    private JPanel footer = new JPanel();

    private Icon bmbImage, missileImage, indiceImage;
    private JButton missileButton, bombButton, indiceButton;
    private BufferedImage image;

    public GamePane() {
        chargerImage("imgs/bg_min.jpg");/// charger l'image de background.
        chargerIcons();/// charger les icones.

        this.nomJoueur = new JLabel(new ImageIcon("imgs/gamer.png"));
        this.niveauActuel = new JLabel(new ImageIcon("imgs/level.png"));
        this.score = new JLabel(new ImageIcon("imgs/star.png"));
        this.conditionsPoints = new JLabel(new ImageIcon("imgs/paper.png"));
        this.conditionsAnimal = new JLabel(new ImageIcon("imgs/pet.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);/// afficher l'image de background.
        /// affichage du nombre points gagnés
        this.score.setText(String.valueOf(this.controller.getPartie().getNbPointsGangerParLeJoueur()));
    }

    public void initialise(MainWindow mainWindow, MainWindowController controller) {

        // initialisation de la fenêtre principale
        this.mainWindow = mainWindow;
        this.controller = controller;
        // affichage du nom du joueur
        this.nomJoueur.setText(this.controller.getJoueur().getnom());
        this.niveauActuel.setText(String.valueOf(this.controller.getJoueur().getniveauActuel()));

        this.conditionsPoints.setText(String
                .valueOf(this.controller.getPartie().getNiveauAJouer().getConditionsDeGagner().getNbPointsAGagner()));
        this.conditionsAnimal.setText(String
                .valueOf(this.controller.getPartie().getNiveauAJouer().getConditionsDeGagner().getNbAnimauxASauver()));
        this.setLayout(new BorderLayout());
        // marges
        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        this.removeAll();
        this.revalidate();
        plateauPanel = new PlateauPanel(mainWindow, controller);

        this.add(plateauPanel, BorderLayout.CENTER);
        footer.removeAll();
        footer.revalidate();

        setHeader();// organiser la vue du header
        setFooter();// organiser la vue du footer

        nomJoueur.setForeground(Color.red);

        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
    }

    public void chargerImage(String chemin) {
        // charger le background
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setFooter() {
        // remplir le footer avec les boutons des aides

        footer.setLayout(new FlowLayout());
        footer.setOpaque(false);

        // rajouter les bouttons des aides
        missileButton = new JButton(String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbMissiles()),
                missileImage);
        bombButton = new JButton(String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbBombes()),
                bmbImage);
        indiceButton = new JButton(String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbIndices()),
                indiceImage);

        footer.add(missileButton);
        footer.add(bombButton);
        footer.add(indiceButton);

        // ajouter les listeners aux bouttons d'aides
        missileButton.addActionListener(e -> {

            // on va vérifier ici si on des missiles
            if (!controller.getPartie().getNiveauAJouer().getAides().missileDisponible()/* ici on fait le test */) {

                JOptionPane.showMessageDialog(this, "Pas de missile disponible !!");
            } else {
                controller.setMissileActive(true);
                missileButton.setText(
                        String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbMissiles() - 1));
                // apartir du niveau 1 ça affiche le nombre de missile -1 une fois on clique
                // dessus
            }

        });
        bombButton.addActionListener(e -> {

            if (!controller.getPartie().getNiveauAJouer().getAides().bombesDisponible()/* ici on fait le test */) {

                JOptionPane.showMessageDialog(this, "Pas de bombe disponible !!");
            } else {
                controller.setBombActive(true);
                bombButton
                        .setText(String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbBombes() - 1));
                // apartir du niveau 1 ça affiche le nombre de bombes - 1 une fois on clique
                // dessus
            }

        });

        indiceButton.addActionListener(e -> {
            // indice boutton c'est le boutton qui affiche la meilleure case a détruire à
            // partir du score que sa destruction peut générer

            if (controller.getPartie().getNiveauAJouer().getAides().indiceDisponible()) {
                // cas ou nous avons deds indices dispo dans notre niveau
                ArrayList<Integer> indiceArrayList;
                try {
                    // la fonction utiliser va rendre utiliser indice
                    indiceArrayList = controller.getPartie().utiliserIndice();

                    JOptionPane.showMessageDialog(this, "*** la meilleure case à detruire est : ligne : "
                            + indiceArrayList.get(0) + " : colonne :" + indiceArrayList.get(1) + "  ***");
                    indiceButton.setText(
                            String.valueOf(controller.getPartie().getNiveauAJouer().getAides().getNbIndices()));
                } catch (CloneNotSupportedException e1) {

                    e1.printStackTrace();
                }

            } else {
                // cas de non diponibilité d'indice
                JOptionPane.showMessageDialog(this, "Pas d'indice disponible !! ");

            }

        });

    }

    public void setHeader() {
        /**
         * le header c'est la partie en haut de notre plateau, elle affiche le nom du
         * joueur le score
         */
        header.setBackground(Color.GREEN);
        header.add(nomJoueur);
        header.add(score);
        header.add(niveauActuel);
        header.add(Box.createRigidArea(new Dimension(30, 0)));
        header.add(conditionsPoints);
        header.add(conditionsAnimal);
    }

    public void chargerIcons() {
        // charger les icones des aides

        try {
            bmbImage = new ImageIcon("imgs/bomb.png");
            missileImage = new ImageIcon("imgs/missile.png");
            indiceImage = new ImageIcon("imgs/indice.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PlateauPanel getPlateauPanel() {
        return plateauPanel;
    }

}
