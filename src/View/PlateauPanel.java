package View;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import Model.Environnement.Plateau;

public class PlateauPanel extends JComponent {

    public PlateauPanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
