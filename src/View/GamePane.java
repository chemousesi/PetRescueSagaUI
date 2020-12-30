package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GamePane extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JLabel nomJoueur = new JLabel("nomJoueur");
    JLabel niveauActuel = new JLabel("niveau 1");
    JLabel score = new JLabel("Score : 0");
    JPanel plateau = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();

    public GamePane() {
        super();
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);

        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        this.add(new PlateauPanel(4, 5), BorderLayout.CENTER);

        header.setBackground(Color.GREEN);
        header.setSize(plateau.getWidth(), 200);
        footer.setBackground(Color.green);

        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        // this.add(createProgressBar(), BorderLayout.SOUTH);

    }

}
