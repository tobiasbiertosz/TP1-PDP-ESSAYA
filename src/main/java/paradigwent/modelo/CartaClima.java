package paradigwent.modelo;

import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.objetivos.Objetivo;

public class CartaClima extends Carta {

    private final EfectoClima efecto;

    public CartaClima(String nombre, Faccion faccion, EfectoClima efecto, String imagen) {
        super(nombre, faccion, imagen);
        this.efecto = efecto;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        tablero.aplicarClima(jugador, this);
    }

    public int modificar(TipoLinea linea, int fuerza) {
        return efecto.modificar(linea, fuerza);
    }

    @Override
    public String descripcion() {
        return "Carta de clima: " + efecto.descripcion();
    }

    @Override
    public TipoDeCarta getTipo() {
        return TipoDeCarta.CLIMA;
    }
}
