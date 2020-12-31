package View;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class AnimalView extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BufferedImage image;

    AnimalView() {
        chargerImage("imgs./dog.png");
    }

    public void chargerImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(image, 20, 20, null);
    }
}
