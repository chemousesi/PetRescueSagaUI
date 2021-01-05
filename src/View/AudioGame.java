package View;

import javax.sound.sampled.*;

import java.io.File;

public class AudioGame {

    /*
     * dans cette classe on fait la gestion des effets de son
     */

    private Clip clip; /// clip pour la musique.
    private Clip tempClip; /// lancer un son.

    public void lanceMusique() { /// pour lancer la musique du jeu.
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/petMusiqueMin.wav")); /// telecharger
                                                                                                                       /// la
                                                                                                                       /// musique
                                                                                                                       /// du
                                                                                                                       /// jeu.
            clip = AudioSystem.getClip();/// récuperer le clip.
            clip.open(audioInputStream);/// ouvrir le clip audio.
            clip.loop(Clip.LOOP_CONTINUOUSLY);/// lancer l'audio en boucle.
            clip.start(); /// lancer l'audio.
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

    public void lanceDestructionSound() { /// lancer le son de destruction de brique.
        lancerSound("sounds/paf2.wav");

    }

    public void lanceGangnerSound() {/// lancer le son de gagner une partie.
        lancerSound("sounds/gagner.wav");
    }

    public void lancePerdreSound() {/// lancer le son quand une partie est perdue.
        lancerSound("sounds/perdre.wav");
    }

    public void stopMusique() { /// arreter une musique.
        clip.close();
    }

    public boolean musiqueEnMarche() {/// voir si une chanson est lancé.
        if (clip == null) {
            return false;
        } else {
            return clip.isOpen();
        }
    }
}
