package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class DuplicarFila extends EfectoConObjetivo {

    public DuplicarFila(Bando bando) {
        super(bando);
    }

    @Override
    protected List<Objetivo> candidatos(Jugador duenio, Tablero tablero) {
        return tablero.objetivosFilas(duenio);
    }

    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        objetivo.duplicarFuerza();
    }

    @Override
    public String descripcion() {
        return "Duplica la fuerza de toda una fila " + getBando().getDescripcion()
                + " a elección, hasta que termine la ronda.";
    }
}
