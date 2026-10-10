package paradigwent.modelo;

import paradigwent.modelo.efectos.EfectoClima;

/**
 * Zona del tablero donde hay como maximo una carta de clima.
 * Se comporta como un EfectoClima: sin clima activo no modifica nada.
 */
public class ZonaDeClima implements EfectoClima {

    private CartaClima cartaActiva;
    private Jugador duenio;

    public void colocar(CartaClima nueva, Jugador jugador) {
        descartarActual();
        cartaActiva = nueva;
        duenio = jugador;
    }

    public void limpiar() {
        descartarActual();
    }

    private void descartarActual() {
        if (cartaActiva != null) {
            duenio.descartar(cartaActiva);
            cartaActiva = null;
            duenio = null;
        }
    }

    @Override
    public int modificar(TipoLinea linea, int fuerza) {
        return cartaActiva == null ? fuerza : cartaActiva.modificar(linea, fuerza);
    }

    @Override
    public String descripcion() {
        return cartaActiva == null ? "Sin clima activo." : cartaActiva.descripcion();
    }

    public CartaClima getCartaActiva() {
        return cartaActiva;
    }

    /** Jugador que puso el clima activo, o null si no hay clima. */
    public Jugador getDuenio() {
        return duenio;
    }
}