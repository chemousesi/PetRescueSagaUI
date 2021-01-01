package View;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Controller.MainWindowController;
import Model.Environnement.Jeu;
import Model.Environnement.Joueur;

import javax.swing.*;
import java.awt.*;

public class ConnexionView extends View {

    // declaration des champs
    JLabel titre = new JLabel("Connexion");
    JPanel formPanel = new JPanel();
    JTextField pseudoTextField = new JTextField();
    JButton connectButton = new JButton("se connecter");
    JButton retourButton = new JButton("retour");

    public ConnexionView(MainWindow mainWindow, MainWindowController mController) {
        super(mainWindow, mController);
        initilize();

        // initilize buttons
    }

    private void initilize() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // titre.setSize(100, 0);// pour avoir tout le texte apparent
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        formPanel.setLayout(new GridLayout(4, 2));

        formPanel.add(new Label("pseudo :"));
        formPanel.add(pseudoTextField);

        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(formPanel);
        this.add(Box.createRigidArea(new DimensionUIResource(0, 200)));

        formPanel.add(connectButton);
        formPanel.add(retourButton);

        connectButton.addActionListener(e -> {
            Joueur joueur = Jeu.connexion(pseudoTextField.getText().trim());
            if (joueur != null) {
                controller.setJoueur(joueur);
                mainWindow.getDeconnexion().setEnabled(true);
                mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "3");
            } else {
                JOptionPane.showMessageDialog(mainWindow, "Le joueur n'existe pas !!");
            }
        });

        retourButton.addActionListener(e -> {
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
        });

    }

}
