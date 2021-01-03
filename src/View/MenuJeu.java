package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import Controller.MainWindowController;
import Model.Environnement.Partie;

public class MenuJeu extends View {

    JButton jouerButton = new JButton(" Jouer ");
    JButton historiqueButton = new JButton("Historique");
    JButton helpButton = new JButton("Aide");
    JButton deconnectButton = new JButton("Deconnexion");
    JPanel buttonsPanel = new JPanel();
    JLabel titreMenu = new JLabel("Menu Jeu");

    public MenuJeu(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        initialize();
    }

    public void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new DimensionUIResource(0, 150)));

        titreMenu.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titreMenu);
        this.setBorder(new EmptyBorder(100, 100, 100, 100));
        this.add(Box.createRigidArea(new DimensionUIResource(0, 20)));

        buttonsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPanel.add(jouerButton);
        buttonsPanel.add(historiqueButton);
        buttonsPanel.add(deconnectButton);
        buttonsPanel.add(helpButton);

        buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(buttonsPanel);

        jouerButton.addActionListener(e -> {
            Partie p = controller.jouerEnModeGraphique();
            if (p != null) {
                mainWindow.getGamePane().initialise(mainWindow, controller);
                mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "4");
            } else {
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

        helpButton.addActionListener(e -> {
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "5");
        });
        historiqueButton.addActionListener((e) -> {
            mainWindow.getHistoriqueView().initialise();
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "6");
        });
        deconnectButton.addActionListener(e -> {
            controller.setJoueur(null);
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
            mainWindow.getDeconnexion().setEnabled(false);
            this.controller.getAudioGame().stopMusique();
        });

    }

}
