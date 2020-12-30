package View;

import java.awt.event.*;
import java.lang.ModuleLayer.Controller;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

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

    JLabel nomJoueur = new JLabel("nomJoueur");
    JLabel niveauActuel = new JLabel("niveau 1");
    JLabel score = new JLabel("Score : 0");
    // JPanel plateau = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();

    public GamePane(MainWindowController controller) {
        super();

        this.controller = controller;

        this.setLayout(new BorderLayout());
        // this.setSize(300, 300);

        this.setBorder(new EmptyBorder(50, 50, 50, 50));

        this.add(new PlateauPanel(4, 4, controller), BorderLayout.CENTER);

        header.setBackground(Color.GREEN);
        header.setSize(200, 200);
        footer.setBackground(Color.green);

        nomJoueur.setForeground(Color.red);
        header.add(nomJoueur, BorderLayout.WEST);
        header.add(score, BorderLayout.EAST);

        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        // this.add(createProgressBar(), BorderLayout.SOUTH);

    }

}
