package paradigwent.modelo;

import java.util.EnumMap;
import java.util.Map;

/**
 * Estado del tablero para ambos jugadores durante una ronda.
 */
public class Tablero {
    private Jugador jugador1;
    private Jugador jugador2;
    private Map<TipoLinea, LineaDeAtaque> lineasJugador1;
    private Map<TipoLinea, LineaDeAtaque> lineasJugador2;
    private CartaClima climaActivo;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.lineasJugador1 = new EnumMap<>(TipoLinea.class);
        this.lineasJugador2 = new EnumMap<>(TipoLinea.class);
        for (TipoLinea tipo : TipoLinea.values()) {
            lineasJugador1.put(tipo, new LineaDeAtaque(tipo));
            lineasJugador2.put(tipo, new LineaDeAtaque(tipo));
        }
    }

    //Método privado "ayudante": dado un jugador, devuelve SU mapa de líneas
    // Lo vamos a reusar en varios métodos de acá abajo.
    private Map<TipoLinea, LineaDeAtaque> lineasDe(Jugador jugador) {
        if (jugador == jugador1) {
            return lineasJugador1;
        } else {
            return lineasJugador2;
        }
    }


    public void colocarCriatura(Jugador jugador, Criatura criatura) {
        Map<TipoLinea, LineaDeAtaque> lineas = lineasDe(jugador);
        LineaDeAtaque linea = lineas.get(criatura.getTipoLinea());
        linea.agregarCriatura(criatura);
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
