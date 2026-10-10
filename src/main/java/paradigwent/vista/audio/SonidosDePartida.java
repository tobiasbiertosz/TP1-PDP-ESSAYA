package paradigwent.vista.audio;

import paradigwent.modelo.Carta;
import paradigwent.modelo.ObservadorDePartida;

/** Hace sonar el efecto que corresponde a cada cosa que pasa en la partida. */
public class SonidosDePartida implements ObservadorDePartida {

    private final ReproductorDeAudio audio;

    public SonidosDePartida(ReproductorDeAudio audio) {
        this.audio = audio;
    }

    @Override
    public void cartaJugada(Carta carta) {
        audio.reproducir(sonidoDe(carta));
    }

    @Override
    public void jugadorPaso() {
        audio.reproducir(Sonido.PASE);
    }

    @Override
    public void rondaTerminada() {
        audio.reproducir(Sonido.FIN_DE_RONDA);
    }

    private Sonido sonidoDe(Carta carta) {
        return switch (carta.getTipo()) {
            case CRIATURA -> Sonido.CARTA_JUGADA;
            case EFECTO -> Sonido.EFECTO;
            case CLIMA -> Sonido.CLIMA;
        };
    }
}
