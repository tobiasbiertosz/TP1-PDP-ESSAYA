package paradigwent.vista.audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

/**
 * Reproduce la musica de fondo y los efectos de sonido.
 * El audio es secundario: si un archivo falta o no se puede reproducir,
 * se avisa por consola y el juego sigue sin ese sonido.
 */
public class ReproductorDeAudio {

    private static final String MUSICA_DE_FONDO = "/audio/musica_fondo_juego.mp3";
    private static final double VOLUMEN_MUSICA = 0.25;
    private static final double VOLUMEN_EFECTOS = 0.8;

    private final Map<Sonido, AudioClip> efectos = new EnumMap<>(Sonido.class);
    private MediaPlayer musica;

    public ReproductorDeAudio() {
        for (Sonido sonido : Sonido.values()) {
            AudioClip efecto = cargarEfecto(sonido);
            if (efecto != null) {
                efectos.put(sonido, efecto);
            }
        }
    }

    public void reproducir(Sonido sonido) {
        AudioClip efecto = efectos.get(sonido);
        if (efecto != null) {
            efecto.play();
        }
    }

    public void iniciarMusicaDeFondo() {
        detenerMusica();
        URL recurso = getClass().getResource(MUSICA_DE_FONDO);
        if (recurso == null) {
            avisarError("No se encontró la música: " + MUSICA_DE_FONDO, null);
            return;
        }
        try {
            musica = new MediaPlayer(new Media(recurso.toExternalForm()));
            musica.setCycleCount(MediaPlayer.INDEFINITE);
            musica.setVolume(VOLUMEN_MUSICA);
            musica.setOnError(() -> avisarError("Error reproduciendo la música de fondo", null));
            musica.play();
        } catch (RuntimeException e) {
            musica = null;
            avisarError("No se pudo reproducir la música de fondo", e);
        }
    }

    public void detenerMusica() {
        if (musica != null) {
            musica.stop();
            musica.dispose();
            musica = null;
        }
    }

    /** Corta la musica y cualquier efecto que este sonando. */
    public void detenerTodo() {
        detenerMusica();
        efectos.values().forEach(AudioClip::stop);
    }

    private AudioClip cargarEfecto(Sonido sonido) {
        URL recurso = getClass().getResource(sonido.getRuta());
        if (recurso == null) {
            avisarError("No se encontró el sonido: " + sonido.getRuta(), null);
            return null;
        }
        try {
            AudioClip efecto = new AudioClip(recurso.toExternalForm());
            efecto.setVolume(VOLUMEN_EFECTOS);
            return efecto;
        } catch (RuntimeException e) {
            avisarError("No se pudo cargar el sonido: " + sonido.getRuta(), e);
            return null;
        }
    }

    private void avisarError(String mensaje, Exception causa) {
        System.err.println(causa == null ? mensaje : mensaje + " (" + causa.getMessage() + ")");
    }
}