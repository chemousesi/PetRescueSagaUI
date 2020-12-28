package View;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import Model.Environnement.Plateau;

public class PlateauPanel extends JPanel {

    public PlateauPanel(Plateau plateau) {
        super();
        this.setLayout(new GridLayout(plateau.getCases().length, plateau.getCases()[0].length));

    }
}
