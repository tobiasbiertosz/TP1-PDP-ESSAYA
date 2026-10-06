package paradigwent.modelo;

import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.objetivos.Objetivo;

public class CartaClima extends Carta {

    private final EfectoClima efecto;

    public CartaClima(String nombre, Faccion faccion, EfectoClima efecto) {
        super(nombre, faccion);
        this.efecto = efecto;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        tablero.aplicarClima(jugador, this);
    }

    public int modificar(TipoLinea linea, int fuerza) {
        return efecto.modificar(linea, fuerza);
    }
}
