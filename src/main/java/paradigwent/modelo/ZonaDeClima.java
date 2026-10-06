package paradigwent.modelo;

import paradigwent.modelo.efectos.EfectoClima;

/**
 * Zona del tablero donde hay como maximo una carta de clima.
 * Se comporta como un EfectoClima: sin clima activo no modifica nada.
 */
public class ZonaDeClima implements EfectoClima {

    private CartaClima cartaActiva;
    private PilaDescarte descarteDelDuenio;

    public void colocar(CartaClima nueva, PilaDescarte descarteDelJugador) {
        descartarActual();
        cartaActiva = nueva;
        descarteDelDuenio = descarteDelJugador;
    }

    public void limpiar() {
        descartarActual();
    }

    private void descartarActual() {
        if (cartaActiva != null) {
            descarteDelDuenio.agregar(cartaActiva);
            cartaActiva = null;
            descarteDelDuenio = null;
        }
    }

    @Override
    public int modificar(TipoLinea linea, int fuerza) {
        return cartaActiva == null ? fuerza : cartaActiva.modificar(linea, fuerza);
    }

    public CartaClima getCartaActiva() {
        return cartaActiva;
    }
}
