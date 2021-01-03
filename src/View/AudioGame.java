package View;

import javax.sound.sampled.*;

import java.io.File;
import java.net.URL;

public class AudioGame {
    private Clip clip;
    private Clip tempClip;

    public void lanceMusique() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/petMusiqueMin.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void lancerSound(String chemin) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(chemin));
            tempClip = AudioSystem.getClip();
            tempClip.open(audioInputStream);
            tempClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void lanceDestructionSound() {
        lancerSound("sounds/paf2.wav");

    }

    public void lanceGangnerSound() {
        lancerSound("sounds/gagner.wav");
    }

    public void lancePerdreSound() {
        lancerSound("sounds/perdre.wav");
    }

    public void stopMusique() {
        clip.close();
    }

    public boolean musiqueEnMarche() {
        if (clip == null) {
            return false;
        } else {
            return clip.isOpen();
        }
    }
}
