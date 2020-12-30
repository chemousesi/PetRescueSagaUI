package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import Controller.MainWindowController;

public class MenuJeu extends View {

    MainWindow mainWindow;
    JButton jouerButton = new JButton(" Jouer ");
    JButton historiqueButton = new JButton("Historique");
    JButton helpButton = new JButton("Aide");
    JButton deconnectButton = new JButton("Deconnexion");
    JPanel buttonsPanel = new JPanel();
    JLabel titreMenu = new JLabel("Menu Jeu");

    public MenuJeu(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        this.mainWindow = mainWindow;
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
            mainWindow.createGameEnvironment();
            // mainWindow.chargerEnvironementJeu();
        });

        deconnectButton.addActionListener(e -> {
            // faudra mettre en déconnexion l'utilisateur dans le model
            controller.setJoueur(null);
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
        });

    }

}
