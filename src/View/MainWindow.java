package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.MainModel;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

public class MainWindow extends JFrame {

    private JButton connectBtn, registerBtn, exitBtn;
    private JPanel jContentPane = null;
    private MainModel model;

    public MainWindow() {
        super();

        this.setTitle("Pet Rescue Saga");
        this.setSize(600, 600);

        this.setContentPane(getJContentPane());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JPanel getJContentPane() {

        if (jContentPane == null) {
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

            connectBtn = new JButton("Connexion");
            registerBtn = new JButton("Inscription");
            exitBtn = new JButton("Quitter");

            panel.add(connectBtn);
            connectBtn.setAlignmentX(CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 30)));

            panel.add(registerBtn);
            panel.add(Box.createRigidArea(new Dimension(0, 30)));
            registerBtn.setAlignmentX(CENTER_ALIGNMENT);

            panel.add(exitBtn);
            exitBtn.setAlignmentX(CENTER_ALIGNMENT);
            panel.setAlignmentY(CENTER_ALIGNMENT);

            exitBtn.addActionListener(e -> System.exit(0));

        }

        return jContentPane;
    }

}