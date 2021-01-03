package View;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObstacleView extends ComponentView {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BufferedImage imageNiv1, imageNiv2;

    ObstacleView(int l, int c) {
        super(l, c);
        imageNiv1 = chargerImage("imgs/obstacle100.png");
        imageNiv2 = chargerImage("imgs/obstacle_min.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(Color.gray);
        // g.fillRect(10, 10, getWidth() - 10, getHeight() - 10);

        if (this.getHeight() < 80) {
            g.drawImage(imageNiv2, 10, 10, null);

        } else {
            g.drawImage(imageNiv1, 10, 10, null);

        }

    }

    public BufferedImage chargerImage(String chemin) {
        try {
            return ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
