package paradigwent.vista.audio;

/** Efectos de sonido del juego y el archivo (en resources) de cada uno. */
public enum Sonido {
    CARTA_JUGADA("/audio/sonido_carta_jugada.mp3"),
    PASE("/audio/pasar_turno.mp3"),
    FIN_DE_RONDA("/audio/termina_ronda.mp3"),
    EFECTO("/audio/efecto.mp3"),
    CLIMA("/audio/clima.mp3"),
    VICTORIA("/audio/winner.mp3"),
    DERROTA("/audio/loser.mp3");

    private final String ruta;

    Sonido(String ruta) {
        this.ruta = ruta;
    }

    String getRuta() {
        return ruta;
    }
}
