package View;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Controller.MainWindowController;

public class AidePanel extends View {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static String consignes[] = { "Cliquer sur la brique à détruire.",
            "Pour utiliser un missile activez le sur l'icone missile et cliquer sur la colonne à détruire.",
            "Pour utiliser une bombe activez la en cliquant sur l'icone bombe et choisissez la case à détruire.",
            "Pour utiliser l'indice activez le en cliquant sur l'icone indice",
            "Une bombe détruit la case sélectionnée avec les deux cases au dessus et en dessous horizentalement.",
            "Indice vous donne la meilleure case à detruire en matière de points.",
            "Le score est calculé par la formule : 2 ^ nombre de cases détruites." };
    private JButton retour = new JButton("Retour");

    AidePanel(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        JLabel title = new JLabel("Aide", new ImageIcon("imgs/indice.png"), 0);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 35));
        this.setBorder(new EmptyBorder(200, 50, 200, 50));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        ImageIcon icone = new ImageIcon("imgs/star.png");
        for (String string : consignes) {
            this.add(new JLabel(string, icone, JLabel.LEFT), BorderLayout.CENTER);
        }
        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.add(retour);
        initialise();
    }

    private void initialise() {
        this.retour.addActionListener((e) -> {
            this.mainWindow.getCardLayout().show(this.mainWindow.getJContentPane(), "3");
        });
    }
}
