package View;

import javax.swing.*;

import Controller.MainWindowController;
import Model.MainModel;
import Model.Environnement.Jeu;

import java.awt.*;

public class MainWindow extends JFrame {

    private JButton connectBtn = new JButton("Se connecter");
    private JButton registerBtn = new JButton("S'inscrire");
    private JButton exitBtn = new JButton("Quitter");
    private JPanel jContentPane = new JPanel();
    private MainModel model;
    private MainWindowController controller = new MainWindowController();
    private InscriptionView inscriptionView = new InscriptionView(this, controller);
    private ConnexionView connexionView = new ConnexionView(this, controller);
    private MenuJeu menuJeu = new MenuJeu(this, controller);
    private CardLayout cl = new CardLayout();
    private GamePane gamePane = new GamePane();

    private JMenuItem deconnexion = new JMenuItem("Deconnexion");
    private JMenuItem exit = new JMenuItem("Quitter");

    public MainWindow() {
        super();
        this.setTitle("Pet Rescue Saga");
        this.setSize(700, 700);

        jContentPane.setLayout(cl);
        initialize();

        Jeu.lancerJeu();
        // this.setContentPane(createJContentPane());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jContentPane.add(welcomeMenu(), "0");
        jContentPane.add(inscriptionView, "2");
        jContentPane.add(connexionView, "1");
        jContentPane.add(menuJeu, "3");
        jContentPane.add(gamePane, "4");

        cl.show(jContentPane, "0");
        this.add(jContentPane);

        this.setJMenuBar(createJMenuBar());

    }

    public void initialize() {

        connectBtn.addActionListener(e -> {
            cl.show(jContentPane, "1"); // "1" pour le jPanel de la connexion
        });

        registerBtn.addActionListener(e -> {
            cl.show(jContentPane, "2");// 2 pour l Jpanel de Inscription
        });

        exitBtn.addActionListener(e -> {
            System.exit(0);
        });

    }

    public JPanel getJContentPane() {
        return jContentPane;
    }

    public CardLayout getCardLayout() {
        return cl;
    }

    public GamePane getGamePane() {
        return gamePane;
    }

    public void chargerEnvironementJeu() {

        // préparation du plateau pour le jeu
        cl.show(jContentPane, "4");
    }

    public JPanel welcomeMenu() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ImageIcon docIcon = new ImageIcon("imgs/dog.png");

        JLabel gameTitle = new JLabel("Pet rescue Saga");
        gameTitle.setIcon(docIcon);

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

        return panel;

    }

    JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JLabel joueurActuel = new JLabel();

        ImageIcon imageIcon = new ImageIcon("imgs/dog.png");
        // Icon icon = new ImageIcon("boy.png");

        joueurActuel.setIcon(imageIcon);
        joueurActuel.setPreferredSize(new Dimension(100, 30));
        joueurActuel.setAlignmentX(RIGHT_ALIGNMENT);

        menuBar.add(joueurActuel);

        // menuBar.setPreferredSize(new DimensionUIResource(this.getWidth(), 20));
        JMenu jeuMenu = new JMenu("Jeu");
        menuBar.add(jeuMenu);

        deconnexion.setEnabled(false);
        jeuMenu.add(deconnexion);

        jeuMenu.add(exit);

        jeuMenu.addSeparator();

        deconnexion.addActionListener((e) -> {
            controller.setJoueur(null);
            if (this.gamePane.getPlateauPanel() != null) {
                this.gamePane.getPlateauPanel().removeAll();
                this.gamePane.getPlateauPanel().revalidate();
            }
            this.getCardLayout().show(this.getJContentPane(), "0");
            this.deconnexion.setEnabled(false);
        });
        exit.addActionListener((e) -> {
            System.exit(0);
        });
        return menuBar;
    }

    public JMenuItem getDeconnexion() {
        return deconnexion;
    }

}