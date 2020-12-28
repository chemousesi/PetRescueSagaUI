package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.MainModel;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MainWindow extends JFrame {

    private JButton connectBtn = new JButton("Se connecter");
    private JButton registerBtn = new JButton("S'inscrire");
    private JButton exitBtn = new JButton("Quitter");
    private JPanel jContentPane = null;
    private MainModel model;
    private InscriptionView inscriptionView = new InscriptionView();
    private ConnexionView connexionView = new ConnexionView();
    private MenuJeu menuJeu = new MenuJeu(this);
    CardLayout cl = new CardLayout();
    GamePane gamePane = new GamePane();

    public MainWindow() {
        super();
        initialize();

        this.setTitle("Pet Rescue Saga");
        this.setSize(600, 600);

        this.setContentPane(createJContentPane());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void initialize() {

        connectBtn.addActionListener(e -> {
            jContentPane.removeAll();
            jContentPane.add(connexionView, BorderLayout.CENTER);
            jContentPane.revalidate();
            jContentPane.repaint();
        });

        registerBtn.addActionListener(e -> {
            jContentPane.removeAll();
            jContentPane.add(inscriptionView, BorderLayout.CENTER);
            jContentPane.revalidate();
            jContentPane.repaint();
        });

        exitBtn.addActionListener(e -> System.exit(0));

        inscriptionView.retourButton.addActionListener(e -> {
            jContentPane.removeAll();
            this.setContentPane(getJContentPane());
            jContentPane.revalidate();
            jContentPane.repaint();
        });
        connexionView.retourButton.addActionListener(e -> {
            jContentPane.removeAll();
            this.setContentPane(getJContentPane());
            jContentPane.revalidate();
            jContentPane.repaint();
        });

        connexionView.connectButton.addActionListener(e -> {
            if (connexionView.pseudoTextField.getText().equals("c")) {
                jContentPane.removeAll();
                jContentPane.add(menuJeu, BorderLayout.CENTER);
                jContentPane.revalidate();
                jContentPane.repaint();
            } else {
                System.out.println("compte innexistant");
            }
        });

        menuJeu.deconnectButton.addActionListener(e -> {
            // faudra mettre en déconnexion l'utilisateur dans le model
            jContentPane.removeAll();
            this.setContentPane(getJContentPane());
            jContentPane.revalidate();
            jContentPane.repaint();
        });

    }

    private JPanel createJContentPane() {

        jContentPane = new JPanel();
        jContentPane.setAlignmentY(CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        jContentPane.add(panel, BorderLayout.CENTER);

        JLabel gameTitle = new JLabel("Pet rescue Saga");
        gameTitle.setSize(100, 0);// pour avoir tout le texte apparent
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        panel.add(gameTitle);
        gameTitle.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        panel.add(connectBtn);
        connectBtn.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        panel.add(registerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(exitBtn);
        exitBtn.setAlignmentX(CENTER_ALIGNMENT);
        panel.setAlignmentY(CENTER_ALIGNMENT);

        return jContentPane;
    }

    public JPanel getJContentPane() {
        return jContentPane;
    }

    public void createGameEnvironment() {

        jContentPane.removeAll();
        // jContentPane.add(gamePane);
        jContentPane = gamePane;
        jContentPane.revalidate();
        jContentPane.repaint();
        // jContentPane.repaint();

    }

    JToolBar CreateGameToolBar() {
        JToolBar jToolBar = new JToolBar();
        jToolBar.add(new JLabel("Niveau1"));

        return jToolBar;
    }

    public void chargerEnvironementJeu() {

        // préparation du plateau pour le jeu
        jContentPane = gamePane;
        jContentPane.repaint();

    }
}