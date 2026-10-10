package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class DuplicarCriatura extends EfectoConObjetivo {

    public DuplicarCriatura(Bando bando) {
        super(bando);
    }

    @Override
    protected List<Objetivo> candidatos(Jugador duenio, Tablero tablero) {
        return tablero.objetivosCriaturas(duenio);
    }

    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        objetivo.duplicarFuerza();
    }

    @Override
    public String descripcion() {
        return "Duplica la fuerza de una criatura " + getBando().getDescripcion() + " a elección.";
    }
}
