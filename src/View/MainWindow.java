package View;

import javax.swing.*;

import Controller.MainWindowController;
import Model.Environnement.Jeu;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

public class MainWindow extends JFrame {

    /**
     * la fenêtre principale du jeu
     * 
     */

    private JButton connectBtn = new JButton("Se connecter");
    private JButton registerBtn = new JButton("S'inscrire");
    private JButton exitBtn = new JButton("Quitter");
    private JPanel jContentPane = new JPanel();

    private MainWindowController controller = new MainWindowController();
    private InscriptionView inscriptionView = new InscriptionView(this, controller);
    private ConnexionView connexionView = new ConnexionView(this, controller);
    private MenuJeu menuJeu = new MenuJeu(this, controller);

    private GamePane gamePane = new GamePane();
    private AidePanel aidePanel = new AidePanel(this, controller);
    private HistoriqueView historiqueView = new HistoriqueView(this, controller);
    // utilisation d'une card layout
    private CardLayout cl = new CardLayout();

    private JMenuItem deconnexion = new JMenuItem("Deconnexion");
    private JMenuItem exit = new JMenuItem("Quitter");

    public MainWindow() {
        super();
        this.setTitle("Pet Rescue Saga");
        this.setSize(700, 700);
        this.setResizable(false);

        jContentPane.setLayout(cl);
        initialize();

        // lancement du jeu dans le model
        Jeu.lancerJeu();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // ajouter toutes les autres vues au card layout avec des descriptions
        jContentPane.add(welcomeMenu(), "0");
        jContentPane.add(inscriptionView, "2");
        jContentPane.add(connexionView, "1");
        jContentPane.add(menuJeu, "3");
        jContentPane.add(gamePane, "4");
        jContentPane.add(aidePanel, "5");
        jContentPane.add(historiqueView, "6");

        // afficher la première vue
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
            Jeu.sauvegarderJoueurs();
            System.exit(0);
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Jeu.sauvegarderJoueurs();
                super.windowClosing(e);
            }
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
        /**
         * création du premier menu au lancement du jeu
         */

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
        /**
         * création du menu bar
         */

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

        JMenu sonMenu = new JMenu("Son");
        menuBar.add(sonMenu);
        JMenuItem arretSon = new JMenuItem("Arrêter son");
        sonMenu.add(arretSon);

        JMenuItem mettreSon = new JMenuItem("Mettre son");
        sonMenu.add(mettreSon);

        mettreSon.addActionListener(e -> {
            if (!this.controller.getAudioGame().musiqueEnMarche())
                this.controller.getAudioGame().lanceMusique();
        });

        arretSon.addActionListener(e -> {
            if (this.controller.getAudioGame().musiqueEnMarche())
                this.controller.getAudioGame().stopMusique();

        });

        deconnexion.addActionListener((e) -> {
            controller.setJoueur(null);
            if (this.gamePane.getPlateauPanel() != null) {
                this.gamePane.getPlateauPanel().removeAll();
                this.gamePane.getPlateauPanel().revalidate();
            }
            this.controller.getAudioGame().stopMusique();
            this.getCardLayout().show(this.getJContentPane(), "0");
            this.deconnexion.setEnabled(false);
        });
        exit.addActionListener((e) -> {
            Jeu.sauvegarderJoueurs();
            System.exit(0);
        });

        return menuBar;
    }

    public JMenuItem getDeconnexion() {
        return deconnexion;
    }

    public HistoriqueView getHistoriqueView() {
        return historiqueView;
    }

}