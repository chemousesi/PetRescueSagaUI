package Model;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainModel {

    BufferedImage image;

    public void setImage(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
