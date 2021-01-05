package View;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.IconUIResource;

import Controller.MainWindowController;
import Model.Environnement.Jeu;
import Model.Environnement.Joueur;

import java.awt.*;

public class InscriptionView extends View {
    /**
     * vue de la page de l'inscription, un nouvel utilisateur
     */

    // declaration des champs
    private JLabel titre = new JLabel("Inscription");
    private JPanel formPanel = new JPanel();
    private JTextField nomTextField = new JTextField();
    private JTextField pseudoTextField = new JTextField();
    private JButton registerButton = new JButton("s'inscrire");
    private JButton retourButton = new JButton("retour");

    public InscriptionView(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        initilize();

    }

    private void initilize() {

        // création d'un box layout orientation verticale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titre.setSize(100, 0);// pour avoir tout le texte apparent
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        // partie formulaire faite dans un gridLayout
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

        // gestion des bouttons
        retourButton.addActionListener(e -> {
            mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "0");
        });
        // enregistrement
        registerButton.addActionListener(e -> {
            String nom = nomTextField.getText().trim();
            String pseudo = pseudoTextField.getText().trim();
            if (!nom.isEmpty() && !pseudo.isEmpty()) {
                if (Jeu.connexion(pseudo) == null) {
                    // joueuer nouveau
                    Joueur joueur = Jeu.creerJoueur(nom, pseudo);
                    this.controller.getAudioGame().lanceMusique();
                    controller.setJoueur(joueur);
                    Jeu.sauvegarderJoueurs();
                    mainWindow.getDeconnexion().setEnabled(true);
                    mainWindow.getCardLayout().show(mainWindow.getJContentPane(), "3");
                } else {
                    // joueur existant
                    JOptionPane.showMessageDialog(mainWindow, "Le joueur existe déjà !!");
                }
            } else {
                // champs non remplis
                JOptionPane.showMessageDialog(mainWindow, "Il faut renseigner tout les champs !!");
            }

        });

    }

}
