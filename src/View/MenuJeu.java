package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class MenuJeu extends JPanel {

    MainWindow mainWindow;
    JButton jouerButton = new JButton(" Jouer ");
    JButton historiqueButton = new JButton("Historique");
    JButton helpButton = new JButton("Aide");
    JButton deconnectButton = new JButton("Deconnexion");
    JPanel buttonsPanel = new JPanel();
    JLabel titreMenu = new JLabel("Menu Jeu");

    public MenuJeu(MainWindow mainWindow) {

        super();
        this.mainWindow = mainWindow;
        initialize();

    }

    public void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new DimensionUIResource(0, 150)));

        titreMenu.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titreMenu);

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
        });

    }

}
