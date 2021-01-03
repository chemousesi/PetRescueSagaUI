package View;

import javax.sound.sampled.*;
import java.net.URL;

public class AudioGame {
    private Clip clip;
    private Clip destructionClip;

    public void lanceMusique() {
        try {
            URL url = getClass().getResource("petMusique.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void lanceDestructionSound() {
        try {
            URL url = getClass().getResource("paf.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            destructionClip = AudioSystem.getClip();
            destructionClip.open(audioInputStream);
            destructionClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void stopMusique() {
        clip.close();
    }

    public void stopDestructionSound() {
        destructionClip.close();
    }

    public boolean musiqueEnMarche() {
        if (clip == null) {
            return false;
        } else {
            return clip.isOpen();
        }
    }
}
