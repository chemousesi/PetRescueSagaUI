package View;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.*;
import java.awt.*;

public class ConnexionView extends JPanel {

    // declaration des champs
    JLabel titre = new JLabel("Connexion");
    JPanel formPanel = new JPanel();
    JTextField pseudoTextField = new JTextField();
    JButton connectButton = new JButton("se connecter");
    JButton retourButton = new JButton("retour");
    MainWindow mainWindow;

    public ConnexionView(MainWindow mainWindow) {
        super();
        initilize();
        this.mainWindow = mainWindow;

        // initilize buttons
    }

    private void initilize() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // titre.setSize(100, 0);// pour avoir tout le texte apparent
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        formPanel.setLayout(new GridLayout(4, 2));

        formPanel.add(new Label("pseudo :"));
        formPanel.add(pseudoTextField);

        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(formPanel);
        this.add(Box.createRigidArea(new DimensionUIResource(0, 200)));

        formPanel.add(connectButton);
        formPanel.add(retourButton);

        connectButton.addActionListener(e -> {
            // il reste un test pour l'accès
            mainWindow.cl.show(mainWindow.getJContentPane(), "3");
        });

        retourButton.addActionListener(e -> {
            mainWindow.cl.show(mainWindow.getJContentPane(), "0");
        });

    }

}
