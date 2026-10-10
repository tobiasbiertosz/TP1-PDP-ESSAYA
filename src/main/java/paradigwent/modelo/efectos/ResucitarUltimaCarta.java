package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.modelo.objetivos.SinObjetivo;

import java.util.List;

/** Devuelve a la mano la ultima carta del descarte del jugador. */
public class ResucitarUltimaCarta implements Efecto {

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return List.of(new SinObjetivo());
    }

    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        jugador.resucitarUltimaCarta();
    }

    @Override
    public String descripcion() {
        return "Devuelve a tu mano la última carta de tu descarte.";
    }
}
