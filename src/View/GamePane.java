package View;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import Controller.MainWindowController;

public class GamePane extends JPanel {

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    static final int GAME_PANE_HEIGHT = 300;
    static final int GAME_PANE_WIDTH = 300;
    static final int UNIT_SIZE = 50; // unité de mesure dans notre programme
    static final int GAME_UNITS = (GAME_PANE_HEIGHT * GAME_PANE_WIDTH) / UNIT_SIZE;
    MainWindowController controller;
    MainWindow mainWindow;

    JLabel nomJoueur;
    JLabel niveauActuel;
    JLabel score;
    // JPanel plateau = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();

    BufferedImage image;

    public GamePane() {
        chargerImage("imgs/bg_min.jpg");
        this.nomJoueur = new JLabel(new ImageIcon("imgs/gamer.png"));
        this.niveauActuel = new JLabel(new ImageIcon("imgs/level.png"));
        this.score = new JLabel(new ImageIcon("imgs/star.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        this.score.setText(String.valueOf(this.controller.getPartie().getNbPointsGangerParLeJoueur()));
    }

    public void initialise(MainWindow mainWindow, MainWindowController controller) {
        this.mainWindow = mainWindow;
        this.controller = controller;
        this.nomJoueur.setText(this.controller.getJoueur().getnom());
        this.niveauActuel.setText(String.valueOf(this.controller.getJoueur().getniveauActuel()));
        this.setLayout(new BorderLayout());
        // this.setBorder(new EmptyBorder(100, 100, 100, 100));
        this.add(new PlateauPanel(mainWindow, controller), BorderLayout.CENTER);
        header.setBackground(Color.GREEN);
        footer.setBackground(Color.green);
        nomJoueur.setForeground(Color.red);
        header.add(nomJoueur, BorderLayout.WEST);
        header.add(score, BorderLayout.EAST);
        header.add(niveauActuel);
        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
    }

    public void chargerImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
