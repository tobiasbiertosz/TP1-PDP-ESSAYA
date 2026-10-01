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
    private Jugador jugadorDelClima

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

    public void aplicarClima(Jugador jugador, CartaClima nuevoClima) {
        if (climaActivo != null) {
            jugadorDelClima.getDescarte().agregar(climaActivo);
        }
        climaActivo = nuevoClima;
        jugadorDelClima = jugador;
    }

        public int calcularFuerzaJugador(Jugador jugador) {
        int total = 0;
        for (LineaDeAtaque linea : lineasDe(jugador).values()) {
            total += linea.calcularFuerzaTotal();
        }
        return total;
    }

    public void limpiarTablero() {
        moverTodoAlDescarte(lineasJugador1, jugador1);
        moverTodoAlDescarte(lineasJugador2, jugador2);

        if (climaActivo != null) {
            jugadorDelClima.getDescarte().agregar(climaActivo);
            climaActivo = null;
            jugadorDelClima = null;
        }
    }

    private void moverTodoAlDescarte(Map<TipoLinea, LineaDeAtaque> lineas, Jugador jugador) {
        for (LineaDeAtaque linea : lineas.values()) {
            for (Criatura criatura : linea.getCriaturas()) {
                jugador.getDescarte().agregar(criatura);
            }
            linea.limpiar();
        }
    }
    public CartaClima getClimaActivo() {
        return climaActivo;
    }
}
