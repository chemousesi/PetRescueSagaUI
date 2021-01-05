package View;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class AnimalView extends ComponentView {

    /**
     * la vue de de l'animal à sauver elle hérite de component view
     */
    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    AnimalView(int l, int c, String chemin) { /// pour afficher un animal dans un plateau.
        super(l, c);
        chargerImage(chemin);
    }

    public void chargerImage(String chemin) {/// pour charger une image.
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 20, 20, null); /// afficher l'image de l'animal.
    }
}
