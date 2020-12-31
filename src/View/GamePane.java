package View;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    JLabel nomJoueur = new JLabel("nomJoueur");
    JLabel niveauActuel = new JLabel("niveau 1");
    JLabel score = new JLabel("Score : 0");
    // JPanel plateau = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();

    private Icon bmbImage, missileImage, indiceImage;

    BufferedImage image;

    public GamePane() {
        chargerImage("imgs/bg_min.jpg");
        chargerIcons();

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void initialise(MainWindow mainWindow, MainWindowController controller) {
        this.mainWindow = mainWindow;
        this.controller = controller;

        this.setLayout(new BorderLayout());

        this.setBorder(new EmptyBorder(100, 100, 100, 100));

        this.add(new PlateauPanel(controller), BorderLayout.CENTER);

        setHeader();// organiser la vue du header
        setFooter();// organiser la vue du footer

        nomJoueur.setForeground(Color.red);

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

    public void setFooter() {
        // remplir le footer avec les boutons des aides

        footer.setLayout(new FlowLayout());
        footer.setOpaque(false);

        JButton missileButton = new JButton(bmbImage);
        JButton bombButton = new JButton(missileImage);
        JButton indiceButton = new JButton(indiceImage);

        missileButton.setOpaque(false);
        bombButton.setOpaque(false);
        bombButton.setOpaque(false);

        footer.add(missileButton);
        footer.add(bombButton);
        footer.add(indiceButton);

        missileButton.addActionListener(e -> {
            controller.setMissileActive(true);

        });
        bombButton.addActionListener(e -> {
            controller.setBombActive(true);
        });

        indiceButton.addActionListener(e -> {
            controller.setIndiceActive(true);
        });

    }

    public void setHeader() {
        header.setBackground(Color.GREEN);
        header.add(nomJoueur, BorderLayout.WEST);
        header.add(score, BorderLayout.EAST);

    }

    public void chargerIcons() {

        try {
            bmbImage = new ImageIcon("imgs/bomb.png");
            missileImage = new ImageIcon("imgs/missile.png");
            indiceImage = new ImageIcon("imgs/indice.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
