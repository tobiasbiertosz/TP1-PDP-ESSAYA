package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.modelo.objetivos.SinObjetivo;

import java.util.List;

/** Efecto nulo: lo usan las criaturas comunes (sin habilidad). */
public class SinEfecto implements Efecto {

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return List.of(new SinObjetivo());
    }

    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        // no hace nada
    }

    @Override
    public String descripcion() {
        return "";
    }
}
