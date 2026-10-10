package paradigwent.modelo;

/**
 * Se entera de lo que va pasando en una Partida (por ejemplo, para reproducir sonidos)
 * sin que la Partida sepa nada de quien la esta mirando.
 */
public interface ObservadorDePartida {

    void cartaJugada(Carta carta);

    void jugadorPaso();

    void rondaTerminada();
}