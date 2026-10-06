package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class EliminarCriatura extends EfectoConObjetivo {

    public EliminarCriatura(Bando bando) {
        super(bando);
    }

    @Override
    protected List<Objetivo> candidatos(Jugador duenio, Tablero tablero) {
        return tablero.objetivosCriaturas(duenio);
    }

    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        objetivo.eliminar();
    }
}
