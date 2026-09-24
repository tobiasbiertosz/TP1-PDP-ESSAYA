package paradigwent.modelo;

import java.util.EnumMap;
import java.util.Map;

/**
 * Estado del tablero para ambos jugadores durante una ronda.
 */
public class Tablero {

    private Map<TipoLinea, LineaDeAtaque> lineasJugador1;
    private Map<TipoLinea, LineaDeAtaque> lineasJugador2;
    private CartaClima climaActivo;

    public Tablero() {
        this.lineasJugador1 = new EnumMap<>(TipoLinea.class);
        this.lineasJugador2 = new EnumMap<>(TipoLinea.class);
        for (TipoLinea tipo : TipoLinea.values()) {
            lineasJugador1.put(tipo, new LineaDeAtaque(tipo));
            lineasJugador2.put(tipo, new LineaDeAtaque(tipo));
        }
    }

    public void colocarCriatura(Jugador jugador, Criatura criatura) {
        // TODO: buscar la linea correspondiente (segun el jugador y el tipoLinea de la criatura)
        // y agregarla ahi con lineaDeAtaque.agregarCriatura(criatura)
    }

    public void aplicarClima(CartaClima clima) {
        // TODO: si ya habia un climaActivo, mandarlo al descarte del jugador que lo jugo
        // luego, guardar "clima" como el nuevo climaActivo
    }

    public int calcularFuerzaJugador(Jugador jugador) {
        // TODO: sumar la fuerza de las 3 lineas de ese jugador (calcularFuerzaTotal de cada una)
        return 0;
    }

    public void limpiarTablero() {
        // TODO: mandar todas las cartas de ambos jugadores al descarte y limpiar las lineas
    }

    public CartaClima getClimaActivo() {
        return climaActivo;
    }
}
