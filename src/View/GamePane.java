package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    public GamePane() {
    }

    public void initialise(MainWindow mainWindow, MainWindowController controller) {
        this.mainWindow = mainWindow;
        this.controller = controller;

        this.setLayout(new BorderLayout());

        this.setBorder(new EmptyBorder(50, 50, 50, 50));

        this.add(new PlateauPanel(controller), BorderLayout.CENTER);

        header.setBackground(Color.GREEN);
        footer.setBackground(Color.green);

        nomJoueur.setForeground(Color.red);
        header.add(nomJoueur, BorderLayout.WEST);
        header.add(score, BorderLayout.EAST);

        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);

    }

}
