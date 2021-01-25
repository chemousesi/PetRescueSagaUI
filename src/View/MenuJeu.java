package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import Controller.MainWindowController;
import Model.Environnement.Partie;

public class MenuJeu extends View {

    /**
     * Le menu du jeu après la connexion ou l'inscription
     */

    private JButton jouerButton = new JButton(" Jouer ");
    private JButton historiqueButton = new JButton("Historique");
    private JButton helpButton = new JButton("Aide");
    private JButton deconnectButton = new JButton("Deconnexion");
    private JPanel buttonsPanel = new JPanel();
    private JLabel titreMenu = new JLabel("Menu Jeu");

    public MenuJeu(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        initialize();
    }

    public void initialize() {
        // initialiser le pane

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new DimensionUIResource(0, 150)));

        titreMenu.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titreMenu);
        this.setBorder(new EmptyBorder(100, 100, 100, 100));
        this.add(Box.createRigidArea(new DimensionUIResource(0, 20)));

        // rajouter les boutons dans la grille
        buttonsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPanel.add(jouerButton);
        buttonsPanel.add(historiqueButton);
        buttonsPanel.add(deconnectButton);
        buttonsPanel.add(helpButton);

        buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(buttonsPanel);

        // boutton du lancement d'une partie
        jouerButton.addActionListener(e -> {
            // lancer une partie dans le backend
            Partie p = controller.jouerEnModeGraphique();
            if (p != null) {
                // cas où le joueur n'a pas fini le jeu.
                mainWindow.getGamePane().initialise(mainWindow, controller);
                mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "4");
            } else {
                // cas où le joueur a fini le jeu.
                int choix = JOptionPane.showConfirmDialog(mainWindow, "Voulez-vous recommencer ?", "Fin Du Jeu",
                        JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {
                    this.controller.getJoueur().setniveauActuel(1);
                    p = controller.jouerEnModeGraphique();
                    mainWindow.getGamePane().initialise(mainWindow, controller);
                    mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "4");
                }
            }
        });

        // rajouter les listeners aux bouttons
        // boutton pour l'affichage des aides
        helpButton.addActionListener(e -> {
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "5");
        });
        // boutton pour l'affichage de l'historique
        historiqueButton.addActionListener((e) -> {
            mainWindow.getHistoriqueView().initialise();
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "6");
        });

        // boutton pour se déconnecter
        deconnectButton.addActionListener(e -> {
            controller.setJoueur(null);
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
            mainWindow.getDeconnexion().setEnabled(false);
            this.controller.getAudioGame().stopMusique();
        });

    }

}
