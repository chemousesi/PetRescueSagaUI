package View;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.IconUIResource;

import java.awt.*;

public class InscriptionView extends JPanel {

    // declaration des champs
    JLabel titre = new JLabel("Inscription");
    JPanel formPanel = new JPanel();
    JTextField nomTextField = new JTextField();
    JTextField pseudoTextField = new JTextField();
    JButton registerButton = new JButton("s'inscrire");
    JButton retourButton = new JButton("retour");
    MainWindow mainWindow;

    public InscriptionView(MainWindow mainWindow) {
        super();
        initilize();
        this.mainWindow = mainWindow;

        // initilize buttons

    }

    private void initilize() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titre.setSize(100, 0);// pour avoir tout le texte apparent
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        formPanel.setLayout(new GridLayout(4, 2));

        formPanel.add(new Label("nom :"));
        formPanel.add(nomTextField);
        formPanel.add(new Label("pseudo :"));
        formPanel.add(pseudoTextField);

        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(formPanel);
        this.add(Box.createRigidArea(new DimensionUIResource(0, 200)));

        formPanel.add(registerButton);
        formPanel.add(retourButton);

        retourButton.addActionListener(e -> {
            mainWindow.cl.show(mainWindow.getJContentPane(), "0");
        });
        registerButton.addActionListener(e -> {
            mainWindow.cl.show(mainWindow.getJContentPane(), "3");

        });

    }

}
