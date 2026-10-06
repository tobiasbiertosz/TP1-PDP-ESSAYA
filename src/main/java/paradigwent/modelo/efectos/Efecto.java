package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

/**
 * Comportamiento especial de una carta de Efecto o de la habilidad de una Criatura.
 */
public interface Efecto {

    /**
     * Objetivos entre los que se puede elegir. Nunca esta vacia: si no hay
     * nada para elegir, devuelve un unico SinObjetivo.
     */
    List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero);

    void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo);
}
