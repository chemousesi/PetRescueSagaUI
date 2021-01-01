package View;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.IconUIResource;

import Controller.MainWindowController;
import Model.Environnement.Jeu;
import Model.Environnement.Joueur;

import java.awt.*;

public class InscriptionView extends View {

    // declaration des champs
    JLabel titre = new JLabel("Inscription");
    JPanel formPanel = new JPanel();
    JTextField nomTextField = new JTextField();
    JTextField pseudoTextField = new JTextField();
    JButton registerButton = new JButton("s'inscrire");
    JButton retourButton = new JButton("retour");

    public InscriptionView(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        initilize();

        // initilize buttons

    }

    private void initilize() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titre.setSize(100, 0);// pour avoir tout le texte apparent
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        formPanel.setLayout(new GridLayout(4, 2));

        formPanel.add(new Label("nom :"));
        formPanel.add(nomTextField);
        formPanel.add(new Label("pseudo :"));
        formPanel.add(pseudoTextField);

        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(formPanel);
        this.add(Box.createRigidArea(new DimensionUIResource(0, 200)));

        formPanel.add(registerButton);
        formPanel.add(retourButton);

        retourButton.addActionListener(e -> {
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
        });
        registerButton.addActionListener(e -> {
            String nom = nomTextField.getText().trim();
            String pseudo = pseudoTextField.getText().trim();
            if (!nom.isEmpty() && !pseudo.isEmpty()) {
                if (Jeu.connexion(pseudo) == null) {
                    Joueur joueur = Jeu.creerJoueur(nom, pseudo);
                    controller.setJoueur(joueur);
                    controller.jouerEnModeGraphique();
                    mainWindow.getGamePane().initialise(mainWindow, controller);
                    mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "3");
                } else {
                    JOptionPane.showMessageDialog(mainWindow, "Le joueur existe déjà !!");
                }
            } else {
                JOptionPane.showMessageDialog(mainWindow, "Il faut renseigner tout les champs !!");
            }

        });

    }

}
