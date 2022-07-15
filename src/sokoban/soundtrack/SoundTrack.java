package sokoban.soundtrack;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundTrack {
    private Clip canción;
    private String nombreCanción;

    public SoundTrack(String nombreCanción) {
        try {
            this.nombreCanción = nombreCanción;
            File file = new File(nombreCanción);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            canción = AudioSystem.getClip();
            canción.open(audioStream);
            reproducir(canción);
            pararCanción(canción);
            reiniciarCanción(canción);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void reiniciarCanción(Clip canción) {
        canción.setMicrosecondPosition(0);
    }

    public void pararCanción(Clip canción) {
        canción.stop();
    }

    public void reproducir(Clip canción) {
        canción.start();
    }

    public Clip getCanción() {
        return canción;
    }
}
